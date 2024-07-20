package tgobmdev.videoapi.component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.repository.CategoryRepository;

@Component
public class CategoryComponent {

  private final CategoryRepository categoryRepository;

  public CategoryComponent(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  private CategoryEntity findDefaultCategory() {
    return findCategoryById(1L).orElseThrow(() -> new ApiException(404, MessageErrorEnum.CODE_3));
  }

  public List<CategoryEntity> findAllCategories() {
    return categoryRepository.findAll();
  }

  public Optional<CategoryEntity> findCategoryById(Long categoryId) {
    return categoryRepository.findById(categoryId);
  }

  public Set<CategoryEntity> findCategoriesByIdIn(Set<Long> categoryIds) {
    return categoryRepository.findByIdIn(categoryIds);
  }

  public Set<CategoryEntity> findCategoriesOrFallbackToDefault(Set<Long> categoryIds) {
    Set<CategoryEntity> categories = new HashSet<>(findCategoriesByIdIn(categoryIds));
    return categories.isEmpty() ? Collections.singleton(findDefaultCategory()) : categories;
  }

  public void deleteCategory(Long categoryId) {
    CategoryEntity categoryEntity = findCategoryById(categoryId).orElseThrow(
        () -> ApiException.of(404, MessageErrorEnum.CODE_2));

    categoryEntity.getVideoEntities()
        .forEach(videoEntity -> videoEntity.getCategoryEntities()
            .remove(categoryEntity));
    categoryRepository.delete(categoryEntity);
  }
}