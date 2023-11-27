package tgobmdev.challengealuraflixapi.core.databridge;

import java.util.List;
import org.springframework.stereotype.Component;
import tgobmdev.challengealuraflixapi.core.mapper.VideoMapper;
import tgobmdev.challengealuraflixapi.core.repository.VideoRepository;
import tgobmdev.challengealuraflixapi.dto.VideoResponse;

@Component
public class VideoDataBridge {

  private final VideoRepository videoRepository;
  private final VideoMapper videoMapper;

  public VideoDataBridge(VideoRepository videoRepository, VideoMapper videoMapper) {
    this.videoRepository = videoRepository;
    this.videoMapper = videoMapper;
  }

  public List<VideoResponse> findAllVideos() {
    return videoMapper.mapToVideoResponses(videoRepository.findAll());
  }
}