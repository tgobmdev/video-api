package tgobmdev.videoapi.service.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.parse.VideoParse;
import tgobmdev.videoapi.service.VideoEditingService;

@Service
public class VideoEditingServiceImpl implements VideoEditingService {

  private final VideoComponent videoComponent;
  private final VideoParse videoParse;

  public VideoEditingServiceImpl(VideoComponent videoComponent, VideoParse videoParse) {
    this.videoComponent = videoComponent;
    this.videoParse = videoParse;
  }

  @Override
  public VideoResponse editVideo(UUID id, VideoRequest videoRequest) {
    VideoEntity videoEntity = videoComponent.editVideo(id, videoRequest) //
        .orElseThrow(() -> ApiException.of(404, MessageErrorEnum.CODIGO_1));
    return videoParse.toResponse(videoEntity);
  }
}