package tgobmdev.videoapi.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.parse.VideoParse;
import tgobmdev.videoapi.service.VideoSearchService;

@Service
public class VideoSearchServiceImpl implements VideoSearchService {

  private final VideoComponent videoComponent;
  private final VideoParse videoParse;

  public VideoSearchServiceImpl(VideoComponent videoComponent, VideoParse videoParse) {
    this.videoComponent = videoComponent;
    this.videoParse = videoParse;
  }

  @Override
  public List<VideoResponse> findAllActiveVideosByTitle(String title) {
    return videoParse.toResponseList(videoComponent.findAllActiveVideosByTitle(title));
  }
}