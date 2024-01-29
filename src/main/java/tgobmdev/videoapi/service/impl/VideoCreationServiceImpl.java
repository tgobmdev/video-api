package tgobmdev.videoapi.service.impl;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.CategoriaComponent;
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.CategoriaEntity;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.parse.VideoParse;
import tgobmdev.videoapi.service.VideoCreationService;
import tgobmdev.videoapi.util.CollectionsUtil;

@Service
public class VideoCreationServiceImpl implements VideoCreationService {

  private final VideoComponent videoComponent;
  private final VideoParse videoParse;
  private final CategoriaComponent categoriaComponent;

  public VideoCreationServiceImpl(VideoComponent videoComponent, VideoParse videoParse,
      CategoriaComponent categoriaComponent) {
    this.videoComponent = videoComponent;
    this.videoParse = videoParse;
    this.categoriaComponent = categoriaComponent;
  }

  @Override
  public VideoResponse createVideo(VideoRequest videoRequest) {
    Set<CategoriaEntity> categorias;

    if (CollectionsUtil.isNullOrEmpty(videoRequest.idsCategoria())) {
      CategoriaEntity categoriaLivre = categoriaComponent.findCategoryById(1L)
          .orElseThrow(() -> new ApiException(404, MessageErrorEnum.CODIGO_3));
      categorias = Collections.singleton(categoriaLivre);
    } else {
      categorias = videoRequest.idsCategoria().stream() //
          .map(idCategoria -> categoriaComponent.findCategoryById(idCategoria)
              .orElseThrow(() -> new ApiException(404, MessageErrorEnum.CODIGO_2)))
          .collect(Collectors.toSet());
    }

    VideoEntity videoEntity = videoParse.createFromRequest(videoRequest);
    videoEntity.setCategoriaEntities(categorias);
    videoComponent.saveVideo(videoEntity);
    return videoParse.toResponse(videoEntity);
  }
}