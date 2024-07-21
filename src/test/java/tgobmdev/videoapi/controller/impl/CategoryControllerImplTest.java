package tgobmdev.videoapi.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tgobmdev.videoapi.dto.request.CategoryRequest;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.mock.CategoryMock;
import tgobmdev.videoapi.service.CategoryService;

@ExtendWith(MockitoExtension.class)
class CategoryControllerImplTest {

  @Mock
  private MockHttpServletRequest mockHttpServletRequest;

  @Mock
  private CategoryService categoryService;

  @InjectMocks
  private CategoryControllerImpl categoryController;

  @BeforeEach
  void setUp() {
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
  }

  @Test
  void givenCategoriesExists_whenFindAllCategories_thenReturnsListOfCategories() {
    List<CategoryResponse> expectedResponses = List.of(CategoryMock.createResponse(),
        CategoryMock.createResponse());

    when(categoryService.findAllCategories()).thenReturn(expectedResponses);
    ResponseEntity<List<CategoryResponse>> responseEntity = categoryController.findAllCategories();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponses, responseEntity.getBody());
    verify(categoryService, times(1)).findAllCategories();
  }

  @Test
  void givenCategoryIdExists_whenFindVideosByCategoryId_thenReturnsCategoryWithVideos() {
    Long categoryId = 1L;
    CategoryResponse expectedResponse = CategoryMock.createResponse();

    when(categoryService.findVideosByCategoryId(categoryId)).thenReturn(expectedResponse);

    ResponseEntity<CategoryResponse> responseEntity = categoryController.findVideosByCategoryId(
        categoryId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponse, responseEntity.getBody());
    verify(categoryService, times(1)).findVideosByCategoryId(categoryId);
  }

  @Test
  void givenVideoIdExists_whenFindCategoryById_thenReturnsCategoryWithoutVideos() {
    Long categoryId = 1L;
    CategoryResponse expectedResponse = CategoryMock.createResponse();

    when(categoryService.findCategoryById(categoryId)).thenReturn(expectedResponse);

    ResponseEntity<CategoryResponse> responseEntity = categoryController.findCategoryById(
        categoryId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponse, responseEntity.getBody());
    verify(categoryService, times(1)).findCategoryById(categoryId);
  }

  @Test
  void givenValidCategoryRequest_whenCreateCategory_thenReturnsCategoryWithLocationHeader() {
    CategoryRequest categoryRequest = CategoryMock.createRequest();
    CategoryResponse expectedResponse = CategoryMock.createResponse();
    URI expectedLocation = URI.create("/categories/" + expectedResponse.id());

    when(mockHttpServletRequest.getRequestURI()).thenReturn("/categories");
    when(categoryService.createCategory(categoryRequest)).thenReturn(expectedResponse);
    ResponseEntity<CategoryResponse> responseEntity = categoryController.createCategory(
        categoryRequest);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(expectedLocation, responseEntity.getHeaders()
        .getLocation());
    verify(categoryService, times(1)).createCategory(categoryRequest);
  }

  @Test
  void givenCategoryIdExists_whenDeleteCategory_thenReturnsNoContent() {
    Long categoryId = 1L;

    ResponseEntity<Void> responseEntity = categoryController.deleteCategory(categoryId);

    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    verify(categoryService, times(1)).deleteCategory(categoryId);
  }
}