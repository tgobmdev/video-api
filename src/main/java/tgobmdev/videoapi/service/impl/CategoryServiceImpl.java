package tgobmdev.videoapi.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
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

  @Override
  public List<CategoryResponse> findAllCategories() {
    return categoryParse.toResponseList(categoryComponent.findAllCategories());
  }

  @Override
  public CategoryResponse findVideosByCategoryId(Long categoryId) {
    CategoryEntity categoryEntity = categoryComponent.findCategoryById(categoryId)
        .orElseThrow(() -> new ApiException(404, MessageErrorEnum.CODE_2));
    return categoryParse.toResponse(categoryEntity);
  }
}