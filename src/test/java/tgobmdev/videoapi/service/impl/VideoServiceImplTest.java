package tgobmdev.videoapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.component.CategoryComponent;
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.dto.request.VideoFilter;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.mapper.VideoMapper;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.mock.VideoMock;

@ExtendWith(MockitoExtension.class)
class VideoServiceImplTest {

  @Mock
  private VideoComponent videoComponent;

  @Mock
  private VideoMapper videoMapper;

  @Mock
  private CategoryComponent categoryComponent;

  @InjectMocks
  private VideoServiceImpl videoService;

  @Test
  void givenActiveVideosExists_whenFindAllActiveVideos_thenReturnsListOfVideos() {
    Set<VideoEntity> videoEntities = Set.of(VideoMock.createEntity(), VideoMock.createEntity());
    List<VideoResponse> expectedResponses = List.of(VideoMock.createResponse(),
        VideoMock.createResponse());
    VideoFilter filter = VideoMock.createFilter();

    when(videoComponent.findAllActiveVideos(filter)).thenReturn(videoEntities);
    when(videoMapper.toResponses(videoEntities)).thenReturn(expectedResponses);
    List<VideoResponse> result = videoService.findAllActiveVideos(filter);

    assertEquals(expectedResponses, result);
    verify(videoComponent, times(1)).findAllActiveVideos(filter);
    verify(videoMapper, times(1)).toResponses(videoEntities);
  }

  @Test
  void givenExistingId_whenFindActiveVideoById_thenReturnsVideos() {
    UUID videoId = UUID.randomUUID();
    VideoEntity videoEntity = VideoMock.createEntity();
    VideoResponse expectedResponse = VideoMock.createResponse();

    when(videoComponent.findActiveVideoById(videoId)).thenReturn(Optional.of(videoEntity));
    when(videoMapper.toResponse(videoEntity)).thenReturn(expectedResponse);
    VideoResponse result = videoService.findActiveVideoById(videoId);

    assertEquals(expectedResponse, result);
    verify(videoComponent, times(1)).findActiveVideoById(videoId);
    verify(videoMapper, times(1)).toResponse(videoEntity);
  }

  @Test
  void givenNonExistingId_whenFindActiveVideoById_thenThrowsApiException() {
    UUID videoId = UUID.randomUUID();

    when(videoComponent.findActiveVideoById(videoId)).thenReturn(Optional.empty());
    ApiException apiException = assertThrows(ApiException.class,
        () -> videoService.findActiveVideoById(videoId), "Video not found.");

    assertEquals(404, apiException.getStatus());
    assertEquals(MessageErrorEnum.CODE_1.getCode(), apiException.getCodeMessage());
    verify(videoComponent, times(1)).findActiveVideoById(videoId);
    verifyNoInteractions(videoMapper);
  }

  @Test
  void givenValidVideoRequest_whenCreateVideo_thenReturnsVideo() {
    VideoRequest videoRequest = VideoMock.createRequest();
    VideoEntity videoEntity = VideoMock.createEntity();
    VideoResponse expectedResponse = VideoMock.createResponse();

    when(videoMapper.toEntity(videoRequest)).thenReturn(videoEntity);
    when(videoMapper.toResponse(videoEntity)).thenReturn(expectedResponse);
    VideoResponse result = videoService.createVideo(videoRequest);

    assertEquals(expectedResponse, result);
    verify(videoMapper, times(1)).toEntity(videoRequest);
    verify(videoComponent, times(1)).saveVideo(videoEntity);
    verify(videoMapper, times(1)).toResponse(videoEntity);
  }

  @Test
  void givenExistingIdAndValidRequest_whenEditVideo_thenReturnsVideoUpdated() {
    UUID videoId = UUID.randomUUID();
    VideoRequest videoRequest = VideoMock.createRequest();
    VideoEntity videoEntity = VideoMock.createEntity();
    VideoResponse expectedResponse = VideoMock.createResponse();

    when(videoComponent.editVideo(videoId, videoRequest)).thenReturn(Optional.of(videoEntity));
    when(videoMapper.toResponse(videoEntity)).thenReturn(expectedResponse);
    VideoResponse result = videoService.editVideo(videoId, videoRequest);

    assertEquals(expectedResponse, result);
    verify(videoComponent, times(1)).editVideo(videoId, videoRequest);
    verify(videoMapper, times(1)).toResponse(videoEntity);
  }

  @Test
  void givenNonExistingIdAndValidRequest_whenEditVideo_thenThrowsApiException() {
    UUID videoId = UUID.randomUUID();
    VideoRequest videoRequest = VideoMock.createRequest();

    when(videoComponent.editVideo(videoId, videoRequest)).thenReturn(Optional.empty());

    ApiException apiException = assertThrows(ApiException.class,
        () -> videoService.editVideo(videoId, videoRequest), "Video not found.");

    assertEquals(404, apiException.getStatus());
    assertEquals(MessageErrorEnum.CODE_1.getCode(), apiException.getCodeMessage());
    assertEquals("Video not found.", apiException.getMessage());
    verify(videoComponent, times(1)).editVideo(videoId, videoRequest);
    verifyNoInteractions(videoMapper);
  }

  @Test
  void givenExistingId_whenDeleteVideo_thenCallsDeleteVideoInComponent() {
    UUID videoId = UUID.randomUUID();

    videoService.deleteVideo(videoId);
    verify(videoComponent, times(1)).deleteVideo(videoId);
  }
}