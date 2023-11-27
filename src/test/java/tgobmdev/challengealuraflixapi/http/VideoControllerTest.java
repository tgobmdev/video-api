package tgobmdev.challengealuraflixapi.http;

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
import tgobmdev.challengealuraflixapi.dto.VideoResponse;
import tgobmdev.challengealuraflixapi.error.exception.ApiException;
import tgobmdev.challengealuraflixapi.mockdata.ApiExceptionMockData;
import tgobmdev.challengealuraflixapi.mockdata.VideoMockData;
import tgobmdev.challengealuraflixapi.service.VideoService;

@ExtendWith(MockitoExtension.class)
public class VideoControllerTest {

  @Mock
  private VideoService videoService;

  @InjectMocks
  private VideoController videoController;

  @Test
  void testFindAllVideos() {
    List<VideoResponse> mockVideoResponses = List.of(VideoMockData.getSampleVideoResponse());

    when(videoService.findAllVideos()).thenReturn(mockVideoResponses);
    ResponseEntity<List<VideoResponse>> responseEntity = videoController.findAllVideos();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockVideoResponses, responseEntity.getBody());
  }

  @Test
  void testFindVideoById() {
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();

    when(videoService.findVideoById(any())).thenReturn(mockVideoResponse);
    ResponseEntity<VideoResponse> result = videoController.findVideoById(UUID.randomUUID());

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(mockVideoResponse, result.getBody());
  }

  @Test
  void testFindVideoByIdNotFound() {
    when(videoService.findVideoById(any())).thenThrow(
        ApiExceptionMockData.getApiExceptionNotFound());

    assertThrows(ApiException.class, () -> videoController.findVideoById(any()));
  }
}