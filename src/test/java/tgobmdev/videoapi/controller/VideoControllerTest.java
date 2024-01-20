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
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.error.exception.ApiException;
import tgobmdev.videoapi.mockdata.ApiExceptionMockData;
import tgobmdev.videoapi.mockdata.VideoMockData;
import tgobmdev.videoapi.service.VideoService;

@ExtendWith(MockitoExtension.class)
public class VideoControllerTest {

  @Mock
  private VideoService videoService;

  @InjectMocks
  private VideoController videoController;

  @Test
  void testFindAllActiveVideos() {
    List<VideoResponse> mockVideoResponses = List.of(VideoMockData.getSampleVideoResponse());

    when(videoService.findAllActiveVideos()).thenReturn(mockVideoResponses);
    ResponseEntity<List<VideoResponse>> responseEntity = videoController.findAllActiveVideos();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockVideoResponses, responseEntity.getBody());
  }

  @Test
  void testFindActiveVideoById() {
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();

    when(videoService.findActiveVideoById(any())).thenReturn(mockVideoResponse);
    ResponseEntity<VideoResponse> result = videoController.findActiveVideoById(UUID.randomUUID());

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(mockVideoResponse, result.getBody());
  }

  @Test
  void testFindActiveVideoByIdNotFound() {
    when(videoService.findActiveVideoById(any())).thenThrow(
        ApiExceptionMockData.getApiExceptionNotFound());

    assertThrows(ApiException.class, () -> videoController.findActiveVideoById(any()));
  }

  @Test
  public void testDeleteVideo() {
    ResponseEntity<Void> result = videoController.deleteVideo(UUID.randomUUID());
    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
  }
}