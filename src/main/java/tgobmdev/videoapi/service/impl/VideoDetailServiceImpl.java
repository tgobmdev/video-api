package tgobmdev.videoapi.service.impl;

import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.parse.VideoParse;
import tgobmdev.videoapi.service.VideoDetailService;

@Service
public class VideoDetailServiceImpl implements VideoDetailService {

  private final VideoComponent videoComponent;
  private final VideoParse videoParse;

  public VideoDetailServiceImpl(VideoComponent videoComponent, VideoParse videoParse) {
    this.videoComponent = videoComponent;
    this.videoParse = videoParse;
  }

  @Override
  public VideoResponse findActiveVideoById(UUID id, HttpHeaders httpHeaders) {
    VideoEntity videoEntity = videoComponent.findActiveVideoById(id) //
        .orElseThrow(() -> ApiException.of(404, MessageErrorEnum.CODIGO_1));
    return videoParse.toResponse(videoEntity);
  }
}