package tgobmdev.videoapi.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.parse.VideoParse;
import tgobmdev.videoapi.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

  private final VideoComponent videoComponent;
  private final VideoParse videoParse;

  public VideoServiceImpl(VideoComponent videoComponent, VideoParse videoParse) {
    this.videoComponent = videoComponent;
    this.videoParse = videoParse;
  }

  @Override
  public List<VideoResponse> findAllActiveVideos() {
    return videoParse.toResponseList(videoComponent.findAllActiveVideos());
  }

  @Override
  public VideoResponse findActiveVideoById(UUID id, HttpHeaders httpHeaders) {
    VideoEntity videoEntity = videoComponent.findActiveVideoById(id) //
        .orElseThrow(() -> ApiException.of(404, MessageErrorEnum.CODIGO_1));
    return videoParse.toResponse(videoEntity);
  }

  @Override
  public VideoResponse createVideo(VideoRequest videoRequest) {
    VideoEntity videoEntity = videoParse.createFromRequest(videoRequest);
    videoComponent.saveVideo(videoEntity);
    return videoParse.toResponse(videoEntity);
  }

  @Override
  public VideoResponse editVideo(UUID id, VideoRequest videoRequest) {
    VideoEntity videoEntity = videoComponent.editVideo(id, videoRequest) //
        .orElseThrow(() -> ApiException.of(404, MessageErrorEnum.CODIGO_1));
    return videoParse.toResponse(videoEntity);
  }

  @Override
  public void deleteVideo(UUID id) {
    videoComponent.deleteVideo(id);
  }
}