package tgobmdev.videoapi.component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.repository.VideoRepository;

@Component
public class VideoComponent {

  private final VideoRepository videoRepository;

  public VideoComponent(VideoRepository videoRepository) {
    this.videoRepository = videoRepository;
  }

  private Optional<VideoEntity> findByIdAndDeletedAtIsNull(UUID id) {
    return videoRepository.findByIdAndDeletedAtIsNull(id);
  }

  private void updateVideo(VideoEntity videoEntity, VideoRequest videoRequest) {
    videoEntity.setTitle(videoRequest.title());
    videoEntity.setDescription(videoRequest.description());
    videoEntity.setUrl(videoRequest.url());
    saveVideo(videoEntity);
  }

  private void softDeleteVideo(VideoEntity videoEntity) {
    videoEntity.setDeleted(true);
    videoEntity.setDeletedAt(LocalDateTime.now());
    saveVideo(videoEntity);
  }

  public List<VideoEntity> findAllActiveVideos() {
    return videoRepository.findAllByDeletedAtIsNull();
  }

  public Optional<VideoEntity> findActiveVideoById(UUID id) {
    return findByIdAndDeletedAtIsNull(id);
  }

  public List<VideoEntity> findAllActiveVideosByTitle(String title) {
    return videoRepository.findByTitleContainingIgnoreCaseAndDeletedAtIsNull(title);
  }

  public void saveVideo(VideoEntity videoEntity) {
    videoRepository.save(videoEntity);
  }

  public Optional<VideoEntity> editVideo(UUID id, VideoRequest videoRequest) {
    return findByIdAndDeletedAtIsNull(id) //
        .map(videoEntity -> {
          updateVideo(videoEntity, videoRequest);
          return videoEntity;
        });
  }

  public void deleteVideo(UUID id) {
    VideoEntity videoEntity = findByIdAndDeletedAtIsNull(id) //
        .orElseThrow(() -> ApiException.of(404, MessageErrorEnum.CODIGO_1));
    softDeleteVideo(videoEntity);
  }
}