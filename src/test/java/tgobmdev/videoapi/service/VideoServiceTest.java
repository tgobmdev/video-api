package tgobmdev.videoapi.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.core.component.VideoComponent;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.error.exception.ApiException;
import tgobmdev.videoapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

  @Mock
  private VideoComponent videoComponent;

  @InjectMocks
  private VideoService videoService;

  @Test
  void givenExistingVideos_whenFindAllActiveVideos_thenReturnListOfVideoResponses() {
    List<VideoResponse> mockVideoResponses = List.of(VideoMockData.getSampleVideoResponse());
    when(videoComponent.findAllActiveVideos()).thenReturn(mockVideoResponses);

    List<VideoResponse> result = videoService.findAllActiveVideos();

    assertEquals(mockVideoResponses, result);
  }

  @Test
  void givenExistingVideoId_whenFindActiveVideoById_thenReturnVideoResponse() {
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();
    when(videoComponent.findActiveVideoById(any())).thenReturn(Optional.of(mockVideoResponse));

    VideoResponse result = videoService.findActiveVideoById(UUID.randomUUID());

    assertEquals(mockVideoResponse, result);
  }

  @Test
  void givenNonExistingVideoId_whenFindActiveVideoById_thenThrowApiException() {
    when(videoComponent.findActiveVideoById(any())).thenReturn(Optional.empty());

    assertThrows(ApiException.class, () -> videoService.findActiveVideoById(UUID.randomUUID()));
  }

  @Test
  void givenVideoCreateRequest_whenCreateVideo_thenNoExceptionShouldBeThrown() {
    when(videoComponent.createVideo(any())).thenReturn(VideoMockData.getSampleVideoResponse());

    assertDoesNotThrow(() -> videoService.createVideo(any()));
  }

  @Test
  void givenExistingVideoId_whenDeleteVideo_thenNoExceptionShouldBeThrown() {
    assertDoesNotThrow(() -> videoService.deleteVideo(any()));
  }
}