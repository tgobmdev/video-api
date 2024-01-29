package tgobmdev.videoapi.service;

import java.util.List;
import tgobmdev.videoapi.dto.response.CategoriaResponse;

public interface CategoriaListService {

  List<CategoriaResponse> findAllCategories();
}