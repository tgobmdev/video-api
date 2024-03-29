package tgobmdev.videoapi.service;

import java.util.List;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.dto.response.CategoryVideoResponse;

public interface CategoryService {

  List<CategoryResponse> findAllCategories();

  CategoryVideoResponse findVideosByCategoryId(Long categoryId);
}