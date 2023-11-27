package tgobmdev.challengealuraflixapi.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import tgobmdev.challengealuraflixapi.core.databridge.VideoDataBridge;
import tgobmdev.challengealuraflixapi.dto.VideoResponse;
import tgobmdev.challengealuraflixapi.error.exception.ApiException;

@Service
public class VideoService {

  private final VideoDataBridge videoDataBridge;

  public VideoService(VideoDataBridge videoDataBridge) {
    this.videoDataBridge = videoDataBridge;
  }

  public List<VideoResponse> findAllVideos() {
    return videoDataBridge.findAllVideos();
  }

  public VideoResponse findVideoById(UUID id) {
    return videoDataBridge.findVideoById(id) //
        .orElseThrow(() -> new ApiException(404, "Video Not Found"));
  }
}