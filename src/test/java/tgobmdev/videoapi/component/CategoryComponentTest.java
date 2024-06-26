package tgobmdev.videoapi.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.mock.CategoryMock;
import tgobmdev.videoapi.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryComponentTest {

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private CategoryComponent categoryComponent;

  @Test
  void givenActiveCategoriesExists_whenFindAllCategories_thenReturnsListOfVideos() {
    when(categoryRepository.findAll()) //
        .thenReturn(List.of(CategoryMock.generateEntity()));

    List<CategoryEntity> result = categoryComponent.findAllCategories();

    assertEquals(1, result.size());
  }

  @Test
  void givenCategoryIdExists_whenFindCategoryById_thenReturnsCategoryEntity() {
    Long categoryId = 1L;
    CategoryEntity categoryEntity = CategoryMock.generateEntity();

    when(categoryRepository.findById(categoryId)) //
        .thenReturn(Optional.of(categoryEntity));

    Optional<CategoryEntity> result = categoryComponent.findCategoryById(categoryId);

    assertEquals(Optional.of(categoryEntity), result);
  }

  @Test
  void givenCategoryIdsIsEmpty_whenFindCategoriesOrFallbackToDefault_thenReturnsDefaultCategory() {
    Long categoryId = 1L;
    Set<Long> categoryIds = Collections.emptySet();
    CategoryEntity categoryEntity = CategoryMock.generateEntity();

    when(categoryRepository.findById(categoryId)) //
        .thenReturn(Optional.of(categoryEntity));
    when(categoryRepository.findByIdIn(categoryIds)) //
        .thenReturn(Collections.emptySet());

    Set<CategoryEntity> result = categoryComponent.findCategoriesOrFallbackToDefault(categoryIds);

    assertEquals(Collections.singleton(categoryEntity), result);
    assertEquals(Collections.emptySet(), categoryEntity.getVideoEntities());
  }

  @Test
  void givenCategoryIdsNotEmpty_whenFindCategoriesOrFallbackToDefault_thenReturnsDefaultCategory() {
    CategoryEntity categoryEntity = CategoryMock.generateEntity();
    Set<Long> categoryIds = Collections.singleton(categoryEntity.getId());

    when(categoryRepository.findByIdIn(categoryIds)) //
        .thenReturn(Collections.singleton(categoryEntity));

    Set<CategoryEntity> result = categoryComponent.findCategoriesOrFallbackToDefault(categoryIds);

    assertEquals(Collections.singleton(categoryEntity), result);
  }
}