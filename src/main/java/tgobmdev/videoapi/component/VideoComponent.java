package tgobmdev.videoapi.component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.error.enumeration.ErrorEnum;
import tgobmdev.videoapi.error.exception.ApiException;
import tgobmdev.videoapi.parse.VideoParse;
import tgobmdev.videoapi.repository.VideoRepository;

@Component
public class VideoComponent {

  private final VideoRepository videoRepository;
  private final VideoParse videoParse;

  public VideoComponent(VideoRepository videoRepository, VideoParse videoParse) {
    this.videoRepository = videoRepository;
    this.videoParse = videoParse;
  }

  private Optional<VideoEntity> findByIdAndDeletedAtIsNull(UUID id) {
    return videoRepository.findByIdAndDeletedAtIsNull(id);
  }

  private void saveVideo(VideoEntity videoEntity) {
    videoRepository.save(videoEntity);
  }

  public List<VideoResponse> findAllActiveVideos() {
    return videoParse.mapToVideoResponses(videoRepository.findAllByDeletedAtIsNull());
  }

  public Optional<VideoResponse> findActiveVideoById(UUID id) {
    return findByIdAndDeletedAtIsNull(id) //
        .map(videoParse::toVideoResponse);
  }

  public VideoResponse createVideo(VideoRequest videoRequest) {
    VideoEntity videoEntity = videoParse.toEntity(videoRequest);
    saveVideo(videoEntity);
    return videoParse.toVideoResponse(videoEntity);
  }

  public Optional<VideoResponse> editVideo(UUID id, VideoRequest videoRequest) {
    return findByIdAndDeletedAtIsNull(id) //
        .map(videoEntity -> {
          editVideo(videoEntity, videoRequest);
          return videoEntity;
        }) //
        .map(videoParse::toVideoResponse);
  }

  private void editVideo(VideoEntity videoEntity, VideoRequest videoRequest) {
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

  public void deleteVideo(UUID id) {
    VideoEntity videoEntity = findByIdAndDeletedAtIsNull(id) //
        .orElseThrow(() -> ApiException.of(404, ErrorEnum.VIDEO_NOT_FOUND));
    softDeleteVideo(videoEntity);
  }
}