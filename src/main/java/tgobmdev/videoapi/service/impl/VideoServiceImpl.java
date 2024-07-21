package tgobmdev.videoapi.service.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.CategoryComponent;
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.dto.request.VideoFilter;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.mapper.VideoMapper;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

  private final VideoComponent videoComponent;
  private final VideoMapper videoMapper;
  private final CategoryComponent categoryComponent;

  public VideoServiceImpl(VideoComponent videoComponent, VideoMapper videoMapper,
      CategoryComponent categoryComponent) {
    this.videoComponent = videoComponent;
    this.videoMapper = videoMapper;
    this.categoryComponent = categoryComponent;
  }

  @Override
  public List<VideoResponse> findAllActiveVideos(VideoFilter filter) {
    return videoMapper.toResponses(videoComponent.findAllActiveVideos(filter));
  }

  @Override
  public VideoResponse findActiveVideoById(UUID videoId) {
    VideoEntity videoEntity = videoComponent.findActiveVideoById(videoId)
        .orElseThrow(() -> ApiException.of(404, MessageErrorEnum.CODE_1));
    return videoMapper.toResponse(videoEntity);
  }

  @Override
  public VideoResponse createVideo(VideoRequest videoRequest) {
    Set<CategoryEntity> categoryEntities = categoryComponent.findCategoriesOrFallbackToDefault(
        videoRequest.categoryIds());

    VideoEntity videoEntity = videoMapper.toEntity(videoRequest);
    videoEntity.setCategoryEntities(categoryEntities);

    videoComponent.saveVideo(videoEntity);
    return videoMapper.toResponse(videoEntity);
  }

  @Override
  public VideoResponse editVideo(UUID videoId, VideoRequest videoRequest) {
    VideoEntity videoEntity = videoComponent.editVideo(videoId, videoRequest)
        .orElseThrow(() -> ApiException.of(404, MessageErrorEnum.CODE_1));
    return videoMapper.toResponse(videoEntity);
  }

  @Override
  public void deleteVideo(UUID videoId) {
    videoComponent.deleteVideo(videoId);
  }
}