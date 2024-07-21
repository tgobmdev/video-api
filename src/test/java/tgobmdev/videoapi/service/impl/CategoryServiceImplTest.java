package tgobmdev.videoapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.component.CategoryComponent;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.mapper.CategoryMapper;
import tgobmdev.videoapi.mock.CategoryMock;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

  @Mock
  private CategoryComponent categoryComponent;

  @Mock
  private CategoryMapper categoryMapper;

  @InjectMocks
  private CategoryServiceImpl categoryService;

  @Test
  void givenActiveCategoriesExists_whenFindAllCategories_thenReturnsListOfVideoResponses() {
    List<CategoryEntity> categoryEntities = List.of(CategoryMock.generateEntity(),
        CategoryMock.generateEntity());
    List<CategoryResponse> expectedResponses = List.of(CategoryMock.createResponse(),
        CategoryMock.createResponse());

    when(categoryComponent.findAllCategories()).thenReturn(categoryEntities);
    when(categoryMapper.toResponses(categoryEntities)).thenReturn(expectedResponses);

    List<CategoryResponse> result = categoryService.findAllCategories();

    assertEquals(expectedResponses, result);
    verify(categoryComponent, times(1)).findAllCategories();
    verify(categoryMapper, times(1)).toResponses(categoryEntities);
  }

  @Test
  void givenExistingCategoryId_whenFindVideosByCategoryId_thenReturnsCategoryResponse() {
    Long categoryId = 1L;
    CategoryEntity categoryEntity = CategoryMock.generateEntity();
    CategoryResponse expectedResponse = CategoryMock.createResponse();

    when(categoryComponent.findCategoryById(categoryId)).thenReturn(Optional.of(categoryEntity));
    when(categoryMapper.toResponse(categoryEntity, Boolean.TRUE)).thenReturn(expectedResponse);

    CategoryResponse result = categoryService.findVideosByCategoryId(categoryId);

    assertEquals(expectedResponse, result);
    verify(categoryComponent, times(1)).findCategoryById(categoryId);
    verify(categoryMapper, times(1)).toResponse(categoryEntity, Boolean.TRUE);
  }

  @Test
  void givenExistingCategoryId_whenFindCategoryById_thenReturnsCategoryResponse() {
    Long categoryId = 1L;
    CategoryEntity categoryEntity = CategoryMock.generateEntity();
    CategoryResponse expectedResponse = CategoryMock.createResponse();

    when(categoryComponent.findCategoryById(categoryId)).thenReturn(Optional.of(categoryEntity));
    when(categoryMapper.toResponse(categoryEntity, Boolean.FALSE)).thenReturn(expectedResponse);

    CategoryResponse result = categoryService.findCategoryById(categoryId);

    assertEquals(expectedResponse, result);
    verify(categoryComponent, times(1)).findCategoryById(categoryId);
    verify(categoryMapper, times(1)).toResponse(categoryEntity, Boolean.FALSE);
  }
}