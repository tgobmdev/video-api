package tgobmdev.videoapi.parse;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;

@Component
public class VideoParse {

  public VideoEntity parseToVideoEntity(VideoRequest videoRequest) {
    VideoEntity videoEntity = new VideoEntity();
    videoEntity.setTitle(videoRequest.title());
    videoEntity.setDescription(videoRequest.description());
    videoEntity.setUrl(videoRequest.url());
    return videoEntity;
  }

  public VideoResponse parseToVideoResponse(VideoEntity video) {
    return new VideoResponse(video.getId(), video.getTitle(), video.getDescription(),
        video.getUrl());
  }

  public List<VideoResponse> parseToVideoResponses(Set<VideoEntity> videos) {
    return videos.stream() //
        .map(this::parseToVideoResponse) //
        .toList();
  }
}