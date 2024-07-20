package tgobmdev.videoapi.service.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.CategoryComponent;
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.parse.VideoParse;
import tgobmdev.videoapi.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

  private final VideoComponent videoComponent;
  private final VideoParse videoParse;
  private final CategoryComponent categoryComponent;

  public VideoServiceImpl(VideoComponent videoComponent, VideoParse videoParse,
      CategoryComponent categoryComponent) {
    this.videoComponent = videoComponent;
    this.videoParse = videoParse;
    this.categoryComponent = categoryComponent;
  }

  private VideoResponse parse(VideoEntity videoEntity) {
    return videoParse.parseToVideoResponse(videoEntity);
  }

  private List<VideoResponse> parse(Set<VideoEntity> videoEntities) {
    return videoParse.parseToVideoResponses(videoEntities);
  }

  @Override
  public List<VideoResponse> findAllActiveVideos() {
    return parse(videoComponent.findAllActiveVideos());
  }

  @Override
  public VideoResponse findActiveVideoById(UUID videoId) {
    VideoEntity videoEntity = videoComponent.findActiveVideoById(videoId)
        .orElseThrow(() -> ApiException.of(404, MessageErrorEnum.CODE_1));
    return parse(videoEntity);
  }

  @Override
  public List<VideoResponse> findAllActiveVideosByTitle(String title) {
    return parse(videoComponent.findAllActiveVideosByTitle(title));
  }

  @Override
  public VideoResponse createVideo(VideoRequest videoRequest) {
    Set<CategoryEntity> categoryEntities = categoryComponent.findCategoriesOrFallbackToDefault(
        videoRequest.categoryIds());

    VideoEntity videoEntity = videoParse.parseToVideoEntity(videoRequest);
    videoEntity.setCategoryEntities(categoryEntities);

    videoComponent.saveVideo(videoEntity);
    return parse(videoEntity);
  }

  @Override
  public VideoResponse editVideo(UUID videoId, VideoRequest videoRequest) {
    VideoEntity videoEntity = videoComponent.editVideo(videoId, videoRequest)
        .orElseThrow(() -> ApiException.of(404, MessageErrorEnum.CODE_1));
    return parse(videoEntity);
  }

  @Override
  public void deleteVideo(UUID videoId) {
    videoComponent.deleteVideo(videoId);
  }
}