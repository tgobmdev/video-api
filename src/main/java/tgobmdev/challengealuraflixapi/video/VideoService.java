package tgobmdev.challengealuraflixapi.video;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

  private final VideoRepository videoRepository;
  private final VideoMapper videoMapper;

  public VideoService(VideoRepository videoRepository, VideoMapper videoMapper) {
    this.videoRepository = videoRepository;
    this.videoMapper = videoMapper;
  }

  public List<VideoResponse> getVideos() {
    var videoEntities = videoRepository.findAll();
    return videoMapper.mapToVideoResponses(videoEntities);
  }
}