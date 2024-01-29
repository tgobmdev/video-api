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
import org.springframework.http.HttpHeaders;
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
  private HttpHeaders httpHeaders;

  @Mock
  private VideoComponent videoComponent;

  @Mock
  private VideoParse videoParse;

  @InjectMocks
  private VideoServiceImpl videoService;

  @Test
  void givenActiveVideosExists_whenFindAllActiveVideos_thenReturnsListOfVideoResponses() {
    Set<VideoEntity> videoEntities = Set.of(VideoMock.generateEntity(), VideoMock.generateEntity());
    List<VideoResponse> expectedResponses = List.of(VideoMock.createResponse(),
        VideoMock.createResponse());

    when(videoComponent.findAllActiveVideos()) //
        .thenReturn(videoEntities);
    when(videoParse.toResponseList(videoEntities)) //
        .thenReturn(expectedResponses);

    List<VideoResponse> result = videoService.findAllActiveVideos();

    assertEquals(expectedResponses, result);
    verify(videoComponent, times(1)).findAllActiveVideos();
    verify(videoParse, times(1)).toResponseList(videoEntities);
  }

  @Test
  void givenExistingId_whenFindActiveVideoById_thenReturnsVideoResponse() {
    UUID videoId = UUID.randomUUID();
    VideoEntity videoEntity = VideoMock.generateEntity();
    VideoResponse expectedResponse = VideoMock.createResponse();

    when(videoComponent.findActiveVideoById(videoId)) //
        .thenReturn(Optional.of(videoEntity));
    when(videoParse.toResponse(videoEntity)) //
        .thenReturn(expectedResponse);

    VideoResponse result = videoService.findActiveVideoById(videoId, httpHeaders);

    assertEquals(expectedResponse, result);
    verify(videoComponent, times(1)).findActiveVideoById(videoId);
    verify(videoParse, times(1)).toResponse(videoEntity);
  }

  @Test
  void givenNonExistingId_whenFindActiveVideoById_thenThrowsApiException() {
    UUID videoId = UUID.randomUUID();
    when(videoComponent.findActiveVideoById(videoId)) //
        .thenReturn(Optional.empty());

    ApiException apiException = assertThrows(ApiException.class,
        () -> videoService.findActiveVideoById(videoId, httpHeaders), "Vídeo não encontrado.");

    assertEquals(404, apiException.getStatus());
    assertEquals(MessageErrorEnum.CODIGO_1.getCode(), apiException.getCodeMessage());
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
    when(videoParse.toResponseList(videoEntities)) //
        .thenReturn(expectedResponses);

    List<VideoResponse> result = videoService.findAllActiveVideosByTitle(title);

    assertEquals(expectedResponses, result);
    verify(videoComponent, times(1)).findAllActiveVideosByTitle(title);
    verify(videoParse, times(1)).toResponseList(videoEntities);
  }

  @Test
  void givenValidVideoRequest_whenCreateVideo_thenReturnsVideoResponse() {
    VideoRequest videoRequest = VideoMock.generateRequest();
    VideoEntity videoEntity = VideoMock.generateEntity();
    VideoResponse expectedResponse = VideoMock.createResponse();

    when(videoParse.createFromRequest(videoRequest)).thenReturn(videoEntity);
    when(videoParse.toResponse(videoEntity)).thenReturn(expectedResponse);

    VideoResponse result = videoService.createVideo(videoRequest);

    assertEquals(expectedResponse, result);
    verify(videoParse, times(1)).createFromRequest(videoRequest);
    verify(videoComponent, times(1)).saveVideo(videoEntity);
    verify(videoParse, times(1)).toResponse(videoEntity);
  }

  @Test
  void givenExistingIdAndValidRequest_whenEditVideo_thenReturnsUpdatedVideoResponse() {
    UUID videoId = UUID.randomUUID();
    VideoRequest videoRequest = VideoMock.generateRequest();
    VideoEntity videoEntity = VideoMock.generateEntity();
    VideoResponse expectedResponse = VideoMock.createResponse();

    when(videoComponent.editVideo(videoId, videoRequest)) //
        .thenReturn(Optional.of(videoEntity));
    when(videoParse.toResponse(videoEntity)) //
        .thenReturn(expectedResponse);

    VideoResponse result = videoService.editVideo(videoId, videoRequest);

    assertEquals(expectedResponse, result);
    verify(videoComponent, times(1)).editVideo(videoId, videoRequest);
    verify(videoParse, times(1)).toResponse(videoEntity);
  }

  @Test
  void givenNonExistingIdAndValidRequest_whenEditVideo_thenThrowsApiException() {
    UUID videoId = UUID.randomUUID();
    VideoRequest videoRequest = VideoMock.generateRequest();

    when(videoComponent.editVideo(videoId, videoRequest)) //
        .thenReturn(Optional.empty());

    ApiException apiException = assertThrows(ApiException.class,
        () -> videoService.editVideo(videoId, videoRequest), "Vídeo não encontrado.");

    assertEquals(404, apiException.getStatus());
    assertEquals(MessageErrorEnum.CODIGO_1.getCode(), apiException.getCodeMessage());
    assertEquals("Vídeo não encontrado.", apiException.getMessage());
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