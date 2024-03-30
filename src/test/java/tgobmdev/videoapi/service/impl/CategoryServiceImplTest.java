package tgobmdev.videoapi.service.impl;

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
    when(categoryParse.toResponseList(categoryEntities)) //
        .thenReturn(expectedResponses);

    List<CategoryResponse> result = categoryService.findAllCategories();

    assertEquals(expectedResponses, result);
    verify(categoryComponent, times(1)).findAllCategories();
    verify(categoryParse, times(1)).toResponseList(categoryEntities);
  }
}