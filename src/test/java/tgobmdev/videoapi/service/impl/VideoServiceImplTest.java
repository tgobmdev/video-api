package tgobmdev.videoapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
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
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.mock.VideoMock;
import tgobmdev.videoapi.parse.VideoParse;

@ExtendWith(MockitoExtension.class)
class VideoServiceImplTest {

  @Mock
  private VideoComponent videoComponent;

  @Mock
  private VideoParse videoParse;

  @Mock
  private CategoryComponent categoryComponent;

  @InjectMocks
  private VideoServiceImpl videoService;

  @Test
  void givenActiveVideosExists_whenFindAllActiveVideos_thenReturnsListOfVideoResponses() {
    Set<VideoEntity> videoEntities = Set.of(VideoMock.generateEntity(), VideoMock.generateEntity());
    List<VideoResponse> expectedResponses = List.of(VideoMock.createResponse(),
        VideoMock.createResponse());

    when(videoComponent.findAllActiveVideos()) //
        .thenReturn(videoEntities);
    when(videoParse.parseToVideoResponses(videoEntities)) //
        .thenReturn(expectedResponses);

    List<VideoResponse> result = videoService.findAllActiveVideos();

    assertEquals(expectedResponses, result);
    verify(videoComponent, times(1)).findAllActiveVideos();
    verify(videoParse, times(1)).parseToVideoResponses(videoEntities);
  }

  @Test
  void givenExistingId_whenFindActiveVideoById_thenReturnsVideoResponse() {
    UUID videoId = UUID.randomUUID();
    VideoEntity videoEntity = VideoMock.generateEntity();
    VideoResponse expectedResponse = VideoMock.createResponse();

    when(videoComponent.findActiveVideoById(videoId)) //
        .thenReturn(Optional.of(videoEntity));
    when(videoParse.parseToVideoResponse(videoEntity)) //
        .thenReturn(expectedResponse);

    VideoResponse result = videoService.findActiveVideoById(videoId);

    assertEquals(expectedResponse, result);
    verify(videoComponent, times(1)).findActiveVideoById(videoId);
    verify(videoParse, times(1)).parseToVideoResponse(videoEntity);
  }

  @Test
  void givenNonExistingId_whenFindActiveVideoById_thenThrowsApiException() {
    UUID videoId = UUID.randomUUID();
    when(videoComponent.findActiveVideoById(videoId)) //
        .thenReturn(Optional.empty());

    ApiException apiException = assertThrows(ApiException.class,
        () -> videoService.findActiveVideoById(videoId), "Video not found.");

    assertEquals(404, apiException.getStatus());
    assertEquals(MessageErrorEnum.CODE_1.getCode(), apiException.getCodeMessage());
    verify(videoComponent, times(1)).findActiveVideoById(videoId);
    verifyNoInteractions(videoParse);
  }

  @Test
  void givenThereAreActiveVideosByTitle_whenFindAllActiveVideosByTitle_thenReturnsListOfVideoResponses() {
    String title = anyString();
    Set<VideoEntity> videoEntities = Set.of(VideoMock.generateEntity(), VideoMock.generateEntity());
    List<VideoResponse> expectedResponses = List.of(VideoMock.createResponse(),
        VideoMock.createResponse());

    when(videoComponent.findAllActiveVideosByTitle(title)) //
        .thenReturn(videoEntities);
    when(videoParse.parseToVideoResponses(videoEntities)) //
        .thenReturn(expectedResponses);

    List<VideoResponse> result = videoService.findAllActiveVideosByTitle(title);

    assertEquals(expectedResponses, result);
    verify(videoComponent, times(1)).findAllActiveVideosByTitle(title);
    verify(videoParse, times(1)).parseToVideoResponses(videoEntities);
  }

  @Test
  void givenValidVideoRequest_whenCreateVideo_thenReturnsVideoResponse() {
    VideoRequest videoRequest = VideoMock.createRequest();
    VideoEntity videoEntity = VideoMock.generateEntity();
    VideoResponse expectedResponse = VideoMock.createResponse();
    when(videoParse.parseToVideoEntity(videoRequest)).thenReturn(videoEntity);
    when(videoParse.parseToVideoResponse(videoEntity)).thenReturn(expectedResponse);

    VideoResponse result = videoService.createVideo(videoRequest);

    assertEquals(expectedResponse, result);
    verify(videoParse, times(1)).parseToVideoEntity(videoRequest);
    verify(videoComponent, times(1)).saveVideo(videoEntity);
    verify(videoParse, times(1)).parseToVideoResponse(videoEntity);
  }

  @Test
  void givenExistingIdAndValidRequest_whenEditVideo_thenReturnsUpdatedVideoResponse() {
    UUID videoId = UUID.randomUUID();
    VideoRequest videoRequest = VideoMock.createRequest();
    VideoEntity videoEntity = VideoMock.generateEntity();
    VideoResponse expectedResponse = VideoMock.createResponse();

    when(videoComponent.editVideo(videoId, videoRequest)) //
        .thenReturn(Optional.of(videoEntity));
    when(videoParse.parseToVideoResponse(videoEntity)) //
        .thenReturn(expectedResponse);

    VideoResponse result = videoService.editVideo(videoId, videoRequest);

    assertEquals(expectedResponse, result);
    verify(videoComponent, times(1)).editVideo(videoId, videoRequest);
    verify(videoParse, times(1)).parseToVideoResponse(videoEntity);
  }

  @Test
  void givenNonExistingIdAndValidRequest_whenEditVideo_thenThrowsApiException() {
    UUID videoId = UUID.randomUUID();
    VideoRequest videoRequest = VideoMock.createRequest();

    when(videoComponent.editVideo(videoId, videoRequest)) //
        .thenReturn(Optional.empty());

    ApiException apiException = assertThrows(ApiException.class,
        () -> videoService.editVideo(videoId, videoRequest), "Video not found.");

    assertEquals(404, apiException.getStatus());
    assertEquals(MessageErrorEnum.CODE_1.getCode(), apiException.getCodeMessage());
    assertEquals("Video not found.", apiException.getMessage());
    verify(videoComponent, times(1)).editVideo(videoId, videoRequest);
    verifyNoInteractions(videoParse);
  }

  @Test
  void givenExistingId_whenDeleteVideo_thenCallsDeleteVideoInComponent() {
    UUID videoId = UUID.randomUUID();

    videoService.deleteVideo(videoId);
    verify(videoComponent, times(1)).deleteVideo(videoId);
  }
}