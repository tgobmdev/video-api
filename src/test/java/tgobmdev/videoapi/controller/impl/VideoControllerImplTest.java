package tgobmdev.videoapi.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.mock.VideoMock;
import tgobmdev.videoapi.service.VideoService;

@ExtendWith(MockitoExtension.class)
class VideoControllerImplTest {

  @Mock
  private MockHttpServletRequest mockHttpServletRequest;

  @Mock
  private VideoService videoService;

  @InjectMocks
  private VideoControllerImpl videoController;

  @BeforeEach
  void setUp() {
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
  }

  @Test
  void givenActiveVideosExists_whenFindAllActiveVideos_thenReturnsListOfVideoResponses() {
    List<VideoResponse> expectedResponses = List.of(VideoMock.createResponse(),
        VideoMock.createResponse());

    when(videoService.findAllActiveVideos()) //
        .thenReturn(expectedResponses);

    ResponseEntity<List<VideoResponse>> responseEntity = videoController.findAllActiveVideos();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponses, responseEntity.getBody());
    verify(videoService, times(1)).findAllActiveVideos();
  }

  @Test
  void givenVideoIdExists_whenFindActiveVideoById_thenReturnsVideoResponse() {
    UUID videoId = UUID.randomUUID();
    VideoResponse expectedResponse = VideoMock.createResponse();

    when(videoService.findActiveVideoById(videoId)) //
        .thenReturn(expectedResponse);

    ResponseEntity<VideoResponse> responseEntity = videoController.findActiveVideoById(videoId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponse, responseEntity.getBody());
    verify(videoService, times(1)).findActiveVideoById(videoId);
  }

  @Test
  void givenThereAreActiveVideosByTitle_whenFindAllActiveVideosByTitle_thenReturnsListOfVideoResponses() {
    String title = anyString();
    List<VideoResponse> expectedResponses = List.of(VideoMock.createResponse(),
        VideoMock.createResponse());

    when(videoService.findAllActiveVideosByTitle(title)) //
        .thenReturn(expectedResponses);

    ResponseEntity<List<VideoResponse>> responseEntity = videoController.findAllActiveVideosByTitle(
        title);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponses, responseEntity.getBody());
    verify(videoService, times(1)).findAllActiveVideosByTitle(title);
  }

  @Test
  void givenValidVideoRequest_whenCreateVideo_thenReturnsCreatedResponseWithLocationHeader() {
    VideoRequest videoRequest = VideoMock.createRequest();
    VideoResponse expectedResponse = VideoMock.createResponse();
    URI expectedLocation = URI.create("/videos/" + expectedResponse.id());

    when(mockHttpServletRequest.getRequestURI()) //
        .thenReturn("/videos");
    when(videoService.createVideo(videoRequest)) //
        .thenReturn(expectedResponse);

    ResponseEntity<VideoResponse> responseEntity = videoController.createVideo(videoRequest);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(expectedLocation, responseEntity.getHeaders()
        .getLocation());
    verify(videoService, times(1)).createVideo(videoRequest);
  }

  @Test
  void givenVideoIdAndRequestExists_whenEditVideo_thenReturnsUpdatedVideoResponse() {
    UUID videoId = UUID.randomUUID();
    VideoRequest videoRequest = VideoMock.createRequest();
    VideoResponse expectedResponse = VideoMock.createResponse();

    when(videoService.editVideo(videoId, videoRequest)) //
        .thenReturn(expectedResponse);

    ResponseEntity<VideoResponse> responseEntity = videoController.editVideo(videoId, videoRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponse, responseEntity.getBody());
    verify(videoService, times(1)).editVideo(videoId, videoRequest);
  }

  @Test
  void givenVideoIdExists_whenDeleteVideo_thenReturnsNoContentResponse() {
    UUID videoId = UUID.randomUUID();

    ResponseEntity<Void> responseEntity = videoController.deleteVideo(videoId);

    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    verify(videoService, times(1)).deleteVideo(videoId);
  }
}