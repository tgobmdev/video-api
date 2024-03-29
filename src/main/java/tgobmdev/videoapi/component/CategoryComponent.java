package tgobmdev.videoapi.component;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.repository.CategoryRepository;

@Component
public class CategoryComponent {

  private final CategoryRepository categoryRepository;

  public CategoryComponent(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<CategoryEntity> findAllCategories() {
    return categoryRepository.findAll();
  }

  public Optional<CategoryEntity> findCategoryById(Long categoryId) {
    return categoryRepository.findById(categoryId);
  }
}