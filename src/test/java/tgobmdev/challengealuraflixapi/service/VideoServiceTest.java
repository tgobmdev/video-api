package tgobmdev.challengealuraflixapi.service;

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
import tgobmdev.challengealuraflixapi.core.component.VideoComponent;
import tgobmdev.challengealuraflixapi.core.entity.VideoEntity;
import tgobmdev.challengealuraflixapi.dto.VideoResponse;
import tgobmdev.challengealuraflixapi.error.exception.ApiException;
import tgobmdev.challengealuraflixapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

  @Mock
  private VideoComponent videoComponent;

  @InjectMocks
  private VideoService videoService;

  @Test
  public void testFindAllActiveVideos() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();
    List<VideoResponse> mockVideoResponses = List.of(
        new VideoResponse(mockVideoEntity.getId(), mockVideoEntity.getTitle(),
            mockVideoEntity.getDescription(), mockVideoEntity.getUrl()));

    when(videoComponent.findAllActiveVideos()).thenReturn(mockVideoResponses);
    List<VideoResponse> result = videoService.findAllActiveVideos();

    assertEquals(mockVideoResponses, result);
  }

  @Test
  void testFindActiveVideoById() {
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();

    when(videoComponent.findActiveVideoById(any())).thenReturn(Optional.of(mockVideoResponse));
    VideoResponse result = videoService.findActiveVideoById(UUID.randomUUID());

    assertEquals(mockVideoResponse, result);
  }

  @Test
  void testFindActiveVideoByIdNotFound() {
    when(videoComponent.findActiveVideoById(any())).thenReturn(Optional.empty());

    assertThrows(ApiException.class, () -> videoService.findActiveVideoById(any()));
  }

  @Test
  public void testDeleteVideo() {
    when(videoComponent.deleteVideo(any())).thenReturn(
        Optional.of(VideoMockData.getVideoDeleteResponse()));

    assertDoesNotThrow(() -> videoService.deleteVideo(any()));
  }

  @Test
  public void testDeleteVideoNotFound() {
    when(videoComponent.deleteVideo(any())).thenReturn(Optional.empty());

    assertThrows(ApiException.class, () -> videoService.deleteVideo(any()));
  }
}