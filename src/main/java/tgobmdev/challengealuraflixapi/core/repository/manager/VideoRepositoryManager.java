package tgobmdev.challengealuraflixapi.core.repository.manager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import tgobmdev.challengealuraflixapi.core.entity.VideoEntity;
import tgobmdev.challengealuraflixapi.core.repository.VideoRepository;

@Component
public class VideoRepositoryManager {

  private final VideoRepository videoRepository;

  public VideoRepositoryManager(VideoRepository videoRepository) {
    this.videoRepository = videoRepository;
  }

  public void saveVideo(VideoEntity videoEntity) {
    videoRepository.save(videoEntity);
  }

  public Optional<VideoEntity> findActiveVideoById(UUID id) {
    return videoRepository.findByIdAndDeletedAtIsNull(id);
  }

  public List<VideoEntity> findAllActiveVideos() {
    return videoRepository.findAllByDeletedAtIsNull();
  }
}