package tgobmdev.videoapi.core.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.core.entity.VideoEntity;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;

@Component
public class VideoMapper {

  public VideoResponse mapToVideoResponse(VideoEntity videoEntity) {
    return new VideoResponse(videoEntity.getId(), videoEntity.getTitle(),
        videoEntity.getDescription(), videoEntity.getUrl());
  }

  public List<VideoResponse> mapToVideoResponses(List<VideoEntity> videoEntities) {
    return videoEntities.stream() //
        .map(this::mapToVideoResponse) //
        .toList();
  }

  public VideoEntity mapToEntity(VideoRequest videoRequest) {
    VideoEntity videoEntity = new VideoEntity();
    videoEntity.setTitle(videoRequest.title());
    videoEntity.setDescription(videoRequest.description());
    videoEntity.setUrl(videoRequest.url());
    return videoEntity;
  }
}