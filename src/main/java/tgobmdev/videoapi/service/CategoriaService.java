package tgobmdev.videoapi.service;

import java.util.List;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.dto.response.CategoriaVideoResponse;

public interface CategoriaService {

  List<CategoriaResponse> findAllCategories();

  CategoriaVideoResponse findVideosByCategoriaId(Long categoriaId);
}