package tgobmdev.videoapi.service;

import java.util.List;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.dto.response.CategoriaVideoResponse;

public interface CategoryService {

  List<CategoriaResponse> findAllCategories();

  CategoriaVideoResponse findVideosByCategoryId(Long categoryId);
}