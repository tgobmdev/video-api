package tgobmdev.videoapi.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.mock.CategoryMock;
import tgobmdev.videoapi.service.CategoryService;

@ExtendWith(MockitoExtension.class)
class CategoryControllerImplTest {

  @Mock
  private CategoryService categoryService;

  @InjectMocks
  private CategoryControllerImpl categoryController;

  @Test
  void givenActiveCategoriesExists_whenFindAllCategories_thenReturnsListOfVideoResponses() {
    List<CategoryResponse> expectedResponses = List.of(CategoryMock.createResponse(),
        CategoryMock.createResponse());

    when(categoryService.findAllCategories()) //
        .thenReturn(expectedResponses);

    ResponseEntity<List<CategoryResponse>> responseEntity = categoryController.findAllCategories();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponses, responseEntity.getBody());
    verify(categoryService, times(1)).findAllCategories();
  }

  @Test
  void givenCategoryIdExists_whenFindVideosByCategoryId_thenReturnsCategoryResponse() {
    Long categoryId = 1L;
    CategoryResponse expectedResponse = CategoryMock.createResponse();

    when(categoryService.findVideosByCategoryId(categoryId)) //
        .thenReturn(expectedResponse);

    ResponseEntity<CategoryResponse> responseEntity = categoryController.findVideosByCategoryId(
        categoryId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponse, responseEntity.getBody());
    verify(categoryService, times(1)).findVideosByCategoryId(categoryId);
  }

  @Test
  void givenVideoIdExists_whenFindCategoryById_thenReturnsVideoResponse() {
    Long categoryId = 1L;
    CategoryResponse expectedResponse = CategoryMock.createResponse();

    when(categoryService.findCategoryById(categoryId)) //
        .thenReturn(expectedResponse);

    ResponseEntity<CategoryResponse> responseEntity = categoryController.findCategoryById(
        categoryId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponse, responseEntity.getBody());
    verify(categoryService, times(1)).findCategoryById(categoryId);
  }
}