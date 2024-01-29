package tgobmdev.videoapi.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.CategoriaComponent;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.CategoriaEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.parse.VideoParse;
import tgobmdev.videoapi.service.VideoCategoriaService;

@Service
public class VideoCategoriaServiceImpl implements VideoCategoriaService {

  private final CategoriaComponent categoriaComponent;
  private final VideoParse videoParse;

  public VideoCategoriaServiceImpl(CategoriaComponent categoriaComponent, VideoParse videoParse) {
    this.categoriaComponent = categoriaComponent;
    this.videoParse = videoParse;
  }

  @Override
  public List<VideoResponse> findByCategoriaEntitiesId(Long idCategoria) {
    CategoriaEntity categoriaEntity = categoriaComponent.findCategoryById(idCategoria)
        .orElseThrow(() -> new ApiException(404, MessageErrorEnum.CODIGO_2));
    return videoParse.toResponseList(categoriaEntity.getVideoEntities());
  }
}