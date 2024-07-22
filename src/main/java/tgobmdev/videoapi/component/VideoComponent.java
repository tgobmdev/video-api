package tgobmdev.videoapi.component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.request.VideoFilter;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.repository.VideoRepository;
import tgobmdev.videoapi.util.FieldUtil;

@Component
public class VideoComponent {

  private final VideoRepository videoRepository;

  public VideoComponent(VideoRepository videoRepository) {
    this.videoRepository = videoRepository;
  }

  private Optional<VideoEntity> findByIdAndDeletedAtIsNull(UUID videoId) {
    return videoRepository.findByIdAndDeletedAtIsNull(videoId);
  }

  private void updateVideo(VideoEntity videoEntity, VideoRequest videoRequest) {
    boolean titleChanged = FieldUtil.acceptIfDifferent(videoEntity::getTitle, videoEntity::setTitle,
        videoRequest.title());
    boolean descriptionChanged = FieldUtil.acceptIfDifferent(videoEntity::getDescription,
        videoEntity::setDescription, videoRequest.description());
    boolean urlChanged = FieldUtil.acceptIfDifferent(videoEntity::getUrl, videoEntity::setUrl,
        videoRequest.url());

    if (titleChanged || descriptionChanged || urlChanged) {
      saveVideo(videoEntity);
    }
  }

  private void softDeleteVideo(VideoEntity videoEntity) {
    videoEntity.setDeleted(true);
    videoEntity.setDeletedAt(LocalDateTime.now());
    saveVideo(videoEntity);
  }

  public Set<VideoEntity> findAllActiveVideos(VideoFilter filter) {
    return videoRepository.findVideosByFilter(filter);
  }

  public Optional<VideoEntity> findActiveVideoById(UUID videoId) {
    return findByIdAndDeletedAtIsNull(videoId);
  }

  public void saveVideo(VideoEntity videoEntity) {
    videoRepository.save(videoEntity);
  }

  public Optional<VideoEntity> editVideo(UUID videoId, VideoRequest videoRequest) {
    return findByIdAndDeletedAtIsNull(videoId).map(videoEntity -> {
      updateVideo(videoEntity, videoRequest);
      return videoEntity;
    });
  }

  public void deleteVideo(UUID videoId) {
    VideoEntity videoEntity = findByIdAndDeletedAtIsNull(videoId).orElseThrow(
        () -> ApiException.of(404, MessageErrorEnum.CODE_1));
    softDeleteVideo(videoEntity);
  }
}