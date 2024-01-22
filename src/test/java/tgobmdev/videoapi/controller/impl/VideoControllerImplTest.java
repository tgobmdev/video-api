package tgobmdev.videoapi.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.error.exception.ApiException;
import tgobmdev.videoapi.mockdata.ApiExceptionMockData;
import tgobmdev.videoapi.mockdata.VideoMockData;
import tgobmdev.videoapi.service.VideoService;

@ExtendWith(MockitoExtension.class)
class VideoControllerImplTest {

  @Mock
  private VideoService videoService;

  @InjectMocks
  private VideoControllerImpl videoControllerImpl;

  @Mock
  private HttpServletRequest request;

  @BeforeEach
  void setUp() {
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
  }

  @Test
  void givenActiveVideosExist_whenFindAllActiveVideos_thenReturnsListOfVideos() {
    List<VideoResponse> mockVideoResponses = List.of(VideoMockData.getSampleVideoResponse());
    when(videoService.findAllActiveVideos()).thenReturn(mockVideoResponses);

    ResponseEntity<List<VideoResponse>> responseEntity = videoControllerImpl.findAllActiveVideos();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockVideoResponses, responseEntity.getBody());
  }

  @Test
  void givenVideoIdExists_whenFindActiveVideoById_thenReturnsVideo() {
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();
    UUID videoId = UUID.randomUUID();
    when(videoService.findActiveVideoById(videoId)).thenReturn(mockVideoResponse);

    ResponseEntity<VideoResponse> result = videoControllerImpl.findActiveVideoById(videoId);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(mockVideoResponse, result.getBody());
  }

  @Test
  void givenVideoIdDoesNotExist_whenFindActiveVideoById_thenThrowsApiExceptionNotFound() {
    UUID nonExistentVideoId = UUID.randomUUID();

    when(videoService.findActiveVideoById(nonExistentVideoId)).thenThrow(
        ApiExceptionMockData.getApiExceptionNotFound());

    assertThrows(ApiException.class,
        () -> videoControllerImpl.findActiveVideoById(nonExistentVideoId));
  }

  @Test
  void givenVideoCreateRequest_whenCreateVideo_thenReturnsCreatedStatus() {
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();
    when(videoService.createVideo(any())).thenReturn(mockVideoResponse);
    when(request.getRequestURI()).thenReturn("/videos");

    ResponseEntity<VideoResponse> result = videoControllerImpl.createVideo(
        VideoMockData.getSampleVideoRequest());

    assertEquals(HttpStatus.CREATED, result.getStatusCode());
  }

  @Test
  void givenVideoIdExists_whenDeleteVideo_thenReturnsNoContentStatus() {
    ResponseEntity<Void> result = videoControllerImpl.deleteVideo(UUID.randomUUID());

    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
  }

  @Test
  void givenVideoIdExists_whenEditVideo_thenReturnsOkStatus() {
    UUID existingVideoId = UUID.randomUUID();
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();

    when(videoService.editVideo(existingVideoId, VideoMockData.getSampleVideoRequest())) //
        .thenReturn(mockVideoResponse);

    ResponseEntity<VideoResponse> result = videoControllerImpl.editVideo(existingVideoId,
        VideoMockData.getSampleVideoRequest());

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(mockVideoResponse, result.getBody());
  }

  @Test
  void givenVideoIdDoesNotExist_whenEditVideo_thenThrowsApiExceptionNotFound() {
    UUID nonExistentVideoId = UUID.randomUUID();

    when(videoService.editVideo(nonExistentVideoId, VideoMockData.getSampleVideoRequest())) //
        .thenThrow(ApiExceptionMockData.getApiExceptionNotFound());

    assertThrows(ApiException.class, () -> videoControllerImpl.editVideo(nonExistentVideoId,
        VideoMockData.getSampleVideoRequest()));
  }
}