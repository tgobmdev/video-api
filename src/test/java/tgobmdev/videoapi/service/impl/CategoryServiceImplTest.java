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
import tgobmdev.videoapi.mock.CategoryMock;
import tgobmdev.videoapi.parse.CategoryParse;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

  @Mock
  private CategoryComponent categoryComponent;

  @Mock
  private CategoryParse categoryParse;

  @InjectMocks
  private CategoryServiceImpl categoryService;

  @Test
  void givenActiveCategoriesExists_whenFindAllCategories_thenReturnsListOfVideoResponses() {
    List<CategoryEntity> categoryEntities = List.of(CategoryMock.generateEntity(),
        CategoryMock.generateEntity());
    List<CategoryResponse> expectedResponses = List.of(CategoryMock.createResponse(),
        CategoryMock.createResponse());

    when(categoryComponent.findAllCategories()) //
        .thenReturn(categoryEntities);
    when(categoryParse.parseToCategoryResponses(categoryEntities, Boolean.FALSE)) //
        .thenReturn(expectedResponses);

    List<CategoryResponse> result = categoryService.findAllCategories();

    assertEquals(expectedResponses, result);
    verify(categoryComponent, times(1)).findAllCategories();
    verify(categoryParse, times(1)).parseToCategoryResponses(categoryEntities, Boolean.FALSE);
  }

  @Test
  void givenExistingCategoryId_whenFindVideosByCategoryId_thenReturnsCategoryResponse() {
    Long categoryId = 1L;
    CategoryEntity categoryEntity = CategoryMock.generateEntity();
    CategoryResponse expectedResponse = CategoryMock.createResponse();

    when(categoryComponent.findCategoryById(categoryId)) //
        .thenReturn(Optional.of(categoryEntity));
    when(categoryParse.parseToCategoryResponse(categoryEntity, Boolean.TRUE)) //
        .thenReturn(expectedResponse);

    CategoryResponse result = categoryService.findVideosByCategoryId(categoryId);

    assertEquals(expectedResponse, result);
    verify(categoryComponent, times(1)).findCategoryById(categoryId);
    verify(categoryParse, times(1)).parseToCategoryResponse(categoryEntity, Boolean.TRUE);
  }
}