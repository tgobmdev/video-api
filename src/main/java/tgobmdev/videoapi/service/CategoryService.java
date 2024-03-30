package tgobmdev.videoapi.service;

import java.util.List;
import tgobmdev.videoapi.dto.response.CategoryResponse;

public interface CategoryService {

  List<CategoryResponse> findAllCategories();

  CategoryResponse findVideosByCategoryId(Long categoryId);
}