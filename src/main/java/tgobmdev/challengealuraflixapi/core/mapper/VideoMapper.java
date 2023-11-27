package tgobmdev.challengealuraflixapi.core.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import tgobmdev.challengealuraflixapi.core.dto.VideoResponse;
import tgobmdev.challengealuraflixapi.core.entity.VideoEntity;

@Component
public class VideoMapper {

  public VideoResponse mapToVideoResponse(VideoEntity videoEntity) {
    return new VideoResponse(videoEntity.getId(), videoEntity.getTitle(),
        videoEntity.getDescription(), videoEntity.getUrl());
  }

  public List<VideoResponse> mapToVideoResponses(List<VideoEntity> videoEntities) {
    return videoEntities.stream().map(this::mapToVideoResponse).collect(Collectors.toList());
  }
}