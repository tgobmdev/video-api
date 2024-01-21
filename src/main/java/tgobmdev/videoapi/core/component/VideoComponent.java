package tgobmdev.videoapi.core.component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.core.entity.VideoEntity;
import tgobmdev.videoapi.core.mapper.VideoMapper;
import tgobmdev.videoapi.core.repository.VideoRepository;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.error.enumeration.ErrorEnum;
import tgobmdev.videoapi.error.exception.ApiException;

@Component
public class VideoComponent {

  private final VideoRepository videoRepository;
  private final VideoMapper videoMapper;

  public VideoComponent(VideoRepository videoRepository, VideoMapper videoMapper) {
    this.videoRepository = videoRepository;
    this.videoMapper = videoMapper;
  }

  private Optional<VideoEntity> findByIdAndDeletedAtIsNull(UUID id) {
    return videoRepository.findByIdAndDeletedAtIsNull(id);
  }

  private void saveVideo(VideoEntity videoEntity) {
    videoRepository.save(videoEntity);
  }

  public List<VideoResponse> findAllActiveVideos() {
    return videoMapper.mapToVideoResponses(videoRepository.findAllByDeletedAtIsNull());
  }

  public Optional<VideoResponse> findActiveVideoById(UUID id) {
    return findByIdAndDeletedAtIsNull(id) //
        .map(videoMapper::mapToVideoResponse);
  }

  public VideoResponse createVideo(VideoRequest videoRequest) {
    VideoEntity videoEntity = videoMapper.mapToEntity(videoRequest);
    saveVideo(videoEntity);
    return videoMapper.mapToVideoResponse(videoEntity);
  }

  private void softDeleteVideo(VideoEntity videoEntity) {
    videoEntity.setDeleted(true);
    videoEntity.setDeletedAt(LocalDateTime.now());
    saveVideo(videoEntity);
  }

  public void deleteVideo(UUID id) {
    VideoEntity videoEntity = findByIdAndDeletedAtIsNull(id) //
        .orElseThrow(() -> ApiException.of(404, ErrorEnum.VIDEO_NOT_FOUND));
    softDeleteVideo(videoEntity);
  }
}