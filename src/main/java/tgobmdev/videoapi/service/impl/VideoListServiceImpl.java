package tgobmdev.videoapi.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.parse.VideoParse;
import tgobmdev.videoapi.service.VideoListService;

@Service
public class VideoListServiceImpl implements VideoListService {

  private final VideoComponent videoComponent;
  private final VideoParse videoParse;

  public VideoListServiceImpl(VideoComponent videoComponent, VideoParse videoParse) {
    this.videoComponent = videoComponent;
    this.videoParse = videoParse;
  }

  @Override
  public List<VideoResponse> findAllActiveVideos() {
    return videoParse.toResponseList(videoComponent.findAllActiveVideos());
  }
}