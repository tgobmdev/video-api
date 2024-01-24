package tgobmdev.videoapi.parse;

import java.util.List;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;

@Component
public class VideoParse {

  public VideoEntity toEntity(VideoRequest videoRequest) {
    VideoEntity videoEntity = new VideoEntity();
    videoEntity.setTitle(videoRequest.title());
    videoEntity.setDescription(videoRequest.description());
    videoEntity.setUrl(videoRequest.url());
    return videoEntity;
  }

  public VideoResponse toVideoResponse(VideoEntity videoEntity) {
    return new VideoResponse(videoEntity.getId(), videoEntity.getTitle(),
        videoEntity.getDescription(), videoEntity.getUrl());
  }

  public List<VideoResponse> toVideoResponseList(List<VideoEntity> videoEntities) {
    return videoEntities.stream() //
        .map(this::toVideoResponse) //
        .toList();
  }
}