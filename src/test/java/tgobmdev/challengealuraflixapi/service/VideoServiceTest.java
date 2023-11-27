package tgobmdev.challengealuraflixapi.service;

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
import tgobmdev.challengealuraflixapi.core.databridge.VideoDataBridge;
import tgobmdev.challengealuraflixapi.core.entity.VideoEntity;
import tgobmdev.challengealuraflixapi.dto.VideoResponse;
import tgobmdev.challengealuraflixapi.error.exception.ApiException;
import tgobmdev.challengealuraflixapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

  @Mock
  private VideoDataBridge videoDataBridge;

  @InjectMocks
  private VideoService videoService;

  @Test
  public void testFindAllVideos() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();
    List<VideoResponse> mockVideoResponses = List.of(
        new VideoResponse(mockVideoEntity.getId(), mockVideoEntity.getTitle(),
            mockVideoEntity.getDescription(), mockVideoEntity.getUrl()));

    when(videoDataBridge.findAllVideos()).thenReturn(mockVideoResponses);
    List<VideoResponse> result = videoService.findAllVideos();

    assertEquals(mockVideoResponses, result);
  }

  @Test
  void testFindVideoById() {
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();

    when(videoDataBridge.findVideoById(any())).thenReturn(Optional.of(mockVideoResponse));
    VideoResponse result = videoService.findVideoById(UUID.randomUUID());

    assertEquals(mockVideoResponse, result);
  }

  @Test
  void testFindVideoByIdNotFound() {
    when(videoDataBridge.findVideoById(any())).thenReturn(Optional.empty());

    assertThrows(ApiException.class, () -> videoService.findVideoById(any()));
  }
}