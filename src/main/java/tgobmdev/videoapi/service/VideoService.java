package tgobmdev.videoapi.service;

import java.util.List;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.core.component.VideoComponent;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.error.enumeration.ErrorEnum;
import tgobmdev.videoapi.error.exception.ApiException;

@Log4j2
@Service
public class VideoService {

  private final VideoComponent videoComponent;

  public VideoService(VideoComponent videoComponent) {
    this.videoComponent = videoComponent;
  }

  public List<VideoResponse> findAllActiveVideos() {
    return videoComponent.findAllActiveVideos();
  }

  public VideoResponse findActiveVideoById(UUID id) {
    return videoComponent.findActiveVideoById(id) //
        .orElseThrow(() -> ApiException.of(404, ErrorEnum.VIDEO_NOT_FOUND));
  }

  public VideoResponse createVideo(VideoRequest videoRequest) {
    return videoComponent.createVideo(videoRequest);
  }

  public void deleteVideo(UUID id) {
    videoComponent.deleteVideo(id);
  }
}