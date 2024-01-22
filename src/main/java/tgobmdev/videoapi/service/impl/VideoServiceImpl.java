package tgobmdev.videoapi.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.core.component.VideoComponent;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.error.enumeration.ErrorEnum;
import tgobmdev.videoapi.error.exception.ApiException;
import tgobmdev.videoapi.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

  private final VideoComponent videoComponent;

  public VideoServiceImpl(VideoComponent videoComponent) {
    this.videoComponent = videoComponent;
  }

  @Override
  public List<VideoResponse> findAllActiveVideos() {
    return videoComponent.findAllActiveVideos();
  }

  @Override
  public VideoResponse findActiveVideoById(UUID id) {
    return videoComponent.findActiveVideoById(id) //
        .orElseThrow(() -> ApiException.of(404, ErrorEnum.VIDEO_NOT_FOUND));
  }

  @Override
  public VideoResponse createVideo(VideoRequest videoRequest) {
    return videoComponent.createVideo(videoRequest);
  }

  @Override
  public VideoResponse editVideo(UUID id, VideoRequest videoRequest) {
    return videoComponent.editVideo(id, videoRequest) //
        .orElseThrow(() -> ApiException.of(404, ErrorEnum.VIDEO_NOT_FOUND));
  }

  @Override
  public void deleteVideo(UUID id) {
    videoComponent.deleteVideo(id);
  }
}