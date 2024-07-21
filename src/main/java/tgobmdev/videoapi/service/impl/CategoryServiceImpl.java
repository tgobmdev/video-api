package tgobmdev.videoapi.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tgobmdev.videoapi.component.CategoryComponent;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.mapper.CategoryMapper;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryComponent categoryComponent;
  private final CategoryMapper categoryMapper;

  public CategoryServiceImpl(CategoryComponent categoryComponent, CategoryMapper categoryMapper) {
    this.categoryComponent = categoryComponent;
    this.categoryMapper = categoryMapper;
  }

  private CategoryEntity obtainCategoryById(Long categoryId) {
    return categoryComponent.findCategoryById(categoryId)
        .orElseThrow(() -> new ApiException(404, MessageErrorEnum.CODE_2));
  }

  @Override
  public List<CategoryResponse> findAllCategories() {
    return categoryMapper.toResponses(categoryComponent.findAllCategories());
  }

  @Override
  @Transactional(readOnly = true)
  public CategoryResponse findVideosByCategoryId(Long categoryId) {
    CategoryEntity categoryEntity = obtainCategoryById(categoryId);
    return categoryMapper.toResponse(categoryEntity, Boolean.TRUE);
  }

  @Override
  public CategoryResponse findCategoryById(Long categoryId) {
    CategoryEntity categoryEntity = obtainCategoryById(categoryId);
    return categoryMapper.toResponse(categoryEntity, Boolean.FALSE);
  }

  @Override
  @Transactional
  public void deleteCategory(Long categoryId) {
    categoryComponent.deleteCategory(categoryId);
  }
}