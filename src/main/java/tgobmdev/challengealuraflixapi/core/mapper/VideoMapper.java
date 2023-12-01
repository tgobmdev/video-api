package tgobmdev.challengealuraflixapi.core.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import tgobmdev.challengealuraflixapi.core.entity.VideoEntity;
import tgobmdev.challengealuraflixapi.dto.VideoCreateRequest;
import tgobmdev.challengealuraflixapi.dto.VideoDeleteResponse;
import tgobmdev.challengealuraflixapi.dto.VideoResponse;

@Component
public class VideoMapper {

  public VideoResponse mapToVideoResponse(VideoEntity videoEntity) {
    return new VideoResponse(videoEntity.getId(), videoEntity.getTitle(),
        videoEntity.getDescription(), videoEntity.getUrl());
  }

  public VideoDeleteResponse mapToVideoDeleteResponse(VideoEntity videoEntity) {
    return new VideoDeleteResponse(videoEntity.getId(), videoEntity.getTitle(),
        videoEntity.getDescription(), videoEntity.getUrl(), videoEntity.getDeletedAt(),
        videoEntity.getDeleted());
  }

  public List<VideoResponse> mapToVideoResponses(List<VideoEntity> videoEntities) {
    return videoEntities.stream().map(this::mapToVideoResponse).collect(Collectors.toList());
  }

  public VideoEntity mapToEntity(VideoCreateRequest videoCreateRequest) {
    VideoEntity videoEntity = new VideoEntity();
    videoEntity.setTitle(videoCreateRequest.title());
    videoEntity.setDescription(videoCreateRequest.description());
    videoEntity.setUrl(videoCreateRequest.url());
    return videoEntity;
  }
}