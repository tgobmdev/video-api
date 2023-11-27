package tgobmdev.challengealuraflixapi.video;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

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