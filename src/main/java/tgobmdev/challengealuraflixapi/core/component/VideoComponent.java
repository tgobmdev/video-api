package tgobmdev.challengealuraflixapi.core.component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import tgobmdev.challengealuraflixapi.core.entity.VideoEntity;
import tgobmdev.challengealuraflixapi.core.mapper.VideoMapper;
import tgobmdev.challengealuraflixapi.core.repository.manager.VideoRepositoryManager;
import tgobmdev.challengealuraflixapi.dto.VideoDeleteResponse;
import tgobmdev.challengealuraflixapi.dto.VideoResponse;

@Component
public class VideoComponent {

  private final VideoRepositoryManager videoRepositoryManager;
  private final VideoMapper videoMapper;

  public VideoComponent(VideoRepositoryManager videoRepositoryManager, VideoMapper videoMapper) {
    this.videoRepositoryManager = videoRepositoryManager;
    this.videoMapper = videoMapper;
  }

  public List<VideoResponse> findAllActiveVideos() {
    return videoMapper.mapToVideoResponses(videoRepositoryManager.findAllActiveVideos());
  }

  public Optional<VideoResponse> findActiveVideoById(UUID id) {
    return videoRepositoryManager.findActiveVideoById(id).map(videoMapper::mapToVideoResponse);
  }

  private void softDeleteVideo(VideoEntity videoEntity) {
    videoEntity.setDeleted(true);
    videoEntity.setDeletedAt(LocalDateTime.now());
  }

  public Optional<VideoDeleteResponse> deleteVideo(UUID id) {
    Optional<VideoEntity> optionalVideoEntity = videoRepositoryManager.findActiveVideoById(id);
    Optional<VideoDeleteResponse> optionalVideoResponse = Optional.empty();

    if (optionalVideoEntity.isPresent()) {
      VideoEntity videoEntity = optionalVideoEntity.get();
      softDeleteVideo(videoEntity);
      videoRepositoryManager.saveVideo(videoEntity);
      optionalVideoResponse = Optional.of(videoMapper.mapToVideoDeleteResponse(videoEntity));
    }
    return optionalVideoResponse;
  }
}