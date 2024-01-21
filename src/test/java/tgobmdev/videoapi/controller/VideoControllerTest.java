package tgobmdev.videoapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.error.exception.ApiException;
import tgobmdev.videoapi.mockdata.ApiExceptionMockData;
import tgobmdev.videoapi.mockdata.VideoMockData;
import tgobmdev.videoapi.service.VideoService;

@ExtendWith(MockitoExtension.class)
class VideoControllerTest {

  @Mock
  private VideoService videoService;

  @InjectMocks
  private VideoController videoController;

  @Test
  void givenActiveVideosExist_whenFindAllActiveVideos_thenReturnsListOfVideos() {
    List<VideoResponse> mockVideoResponses = List.of(VideoMockData.getSampleVideoResponse());
    when(videoService.findAllActiveVideos()).thenReturn(mockVideoResponses);

    ResponseEntity<List<VideoResponse>> responseEntity = videoController.findAllActiveVideos();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockVideoResponses, responseEntity.getBody());
  }

  @Test
  void givenVideoIdExists_whenFindActiveVideoById_thenReturnsVideo() {
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();
    UUID videoId = UUID.randomUUID();
    when(videoService.findActiveVideoById(videoId)).thenReturn(mockVideoResponse);

    ResponseEntity<VideoResponse> result = videoController.findActiveVideoById(videoId);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(mockVideoResponse, result.getBody());
  }

  @Test
  void givenVideoIdDoesNotExist_whenFindActiveVideoById_thenThrowsApiExceptionNotFound() {
    UUID nonExistentVideoId = UUID.randomUUID();

    when(videoService.findActiveVideoById(nonExistentVideoId)).thenThrow(
        ApiExceptionMockData.getApiExceptionNotFound());

    assertThrows(ApiException.class, () -> videoController.findActiveVideoById(nonExistentVideoId));
  }

  @Test
  void givenVideoCreateRequest_whenCreateVideo_thenReturnsCreatedStatus() {
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();
    when(videoService.createVideo(any())).thenReturn(mockVideoResponse);

    ResponseEntity<VideoResponse> result = videoController.createVideo(
        VideoMockData.getVideoCreateRequest(), UriComponentsBuilder.newInstance());

    assertEquals(HttpStatus.CREATED, result.getStatusCode());
  }

  @Test
  void givenVideoIdExists_whenDeleteVideo_thenReturnsNoContentStatus() {
    ResponseEntity<Void> result = videoController.deleteVideo(UUID.randomUUID());

    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
  }
}