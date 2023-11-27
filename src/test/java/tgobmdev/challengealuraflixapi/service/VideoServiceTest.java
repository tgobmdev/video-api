package tgobmdev.challengealuraflixapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.challengealuraflixapi.core.databridge.VideoDataBridge;
import tgobmdev.challengealuraflixapi.core.entity.VideoEntity;
import tgobmdev.challengealuraflixapi.dto.VideoResponse;
import tgobmdev.challengealuraflixapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

  @Mock
  private VideoDataBridge videoDataBridge;

  @InjectMocks
  private VideoService videoService;

  @Test
  public void testFindAllVideos() {
    VideoEntity videoEntity = VideoMockData.getSampleVideoEntity();

    List<VideoResponse> mockVideoResponses = List.of(
        new VideoResponse(videoEntity.getId(), videoEntity.getTitle(), videoEntity.getDescription(),
            videoEntity.getUrl()));

    when(videoDataBridge.findAllVideos()).thenReturn(mockVideoResponses);

    List<VideoResponse> result = videoService.findAllVideos();

    assertEquals(mockVideoResponses, result);
  }
}