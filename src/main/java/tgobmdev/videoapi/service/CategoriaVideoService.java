package tgobmdev.videoapi.service;

import java.util.List;
import tgobmdev.videoapi.dto.response.VideoResponse;

public interface CategoriaVideoService {

  List<VideoResponse> findByCategoriaEntitiesId(Long idCategoria);
}