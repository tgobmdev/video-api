package tgobmdev.videoapi.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tgobmdev.videoapi.component.CategoryComponent;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.parse.CategoryParse;
import tgobmdev.videoapi.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryComponent categoryComponent;
  private final CategoryParse categoryParse;

  public CategoryServiceImpl(CategoryComponent categoryComponent, CategoryParse categoryParse) {
    this.categoryComponent = categoryComponent;
    this.categoryParse = categoryParse;
  }

  private CategoryResponse parse(CategoryEntity categoryEntity, boolean includeVideos) {
    return categoryParse.parseToCategoryResponse(categoryEntity, includeVideos);
  }

  private CategoryEntity obtainCategoryById(Long categoryId) {
    return categoryComponent.findCategoryById(categoryId)
        .orElseThrow(() -> new ApiException(404, MessageErrorEnum.CODE_2));
  }

  @Override
  public List<CategoryResponse> findAllCategories() {
    return categoryParse.parseToCategoryResponses(categoryComponent.findAllCategories(),
        Boolean.FALSE);
  }

  @Override
  @Transactional(readOnly = true)
  public CategoryResponse findVideosByCategoryId(Long categoryId) {
    CategoryEntity categoryEntity = obtainCategoryById(categoryId);
    return parse(categoryEntity, Boolean.TRUE);
  }

  @Override
  public CategoryResponse findCategoryById(Long categoryId) {
    CategoryEntity categoryEntity = obtainCategoryById(categoryId);
    return parse(categoryEntity, Boolean.FALSE);
  }

  @Override
  @Transactional
  public void deleteCategory(Long categoryId) {
    categoryComponent.deleteCategory(categoryId);
  }
}