package tgobmdev.videoapi.service;

import java.util.List;
import tgobmdev.videoapi.dto.request.CategoryRequest;
import tgobmdev.videoapi.dto.response.CategoryResponse;

public interface CategoryService {

  List<CategoryResponse> findAllCategories();

  CategoryResponse findVideosByCategoryId(Long categoryId);

  CategoryResponse findCategoryById(Long categoryId);

  CategoryResponse createCategory(CategoryRequest categoryRequest);

  void deleteCategory(Long categoryId);
}