package tgobmdev.videoapi.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.core.component.VideoComponent;
import tgobmdev.videoapi.dto.VideoCreateRequest;
import tgobmdev.videoapi.dto.VideoResponse;
import tgobmdev.videoapi.error.exception.ApiException;

@Service
public class VideoService {

  public static final String VIDEO_NOT_FOUND = "Video Not Found";

  private final VideoComponent videoComponent;

  public VideoService(VideoComponent videoComponent) {
    this.videoComponent = videoComponent;
  }

  public List<VideoResponse> findAllActiveVideos() {
    return videoComponent.findAllActiveVideos();
  }

  public VideoResponse findActiveVideoById(UUID id) {
    return videoComponent.findActiveVideoById(id) //
        .orElseThrow(() -> new ApiException(404, VIDEO_NOT_FOUND));
  }

  public void deleteVideo(UUID id) {
    videoComponent.deleteVideo(id).orElseThrow(() -> new ApiException(404, VIDEO_NOT_FOUND));
  }

  public VideoResponse createVideo(VideoCreateRequest videoCreateRequest) {
    return videoComponent.createVideo(videoCreateRequest);
  }
}