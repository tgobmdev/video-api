package tgobmdev.challengealuraflixapi.service;

import java.util.List;
import org.springframework.stereotype.Service;
import tgobmdev.challengealuraflixapi.core.databridge.VideoDataBridge;
import tgobmdev.challengealuraflixapi.core.dto.VideoResponse;

@Service
public class VideoService {

  private final VideoDataBridge videoDataBridge;

  public VideoService(VideoDataBridge videoDataBridge) {
    this.videoDataBridge = videoDataBridge;
  }

  public List<VideoResponse> findAllVideos() {
    return videoDataBridge.findAllVideos();
  }
}