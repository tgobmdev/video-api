package tgobmdev.challengealuraflixapi.video;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.challengealuraflixapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
public class VideoMapperTest {

  @Mock
  private VideoEntity videoEntity;

  @InjectMocks
  private VideoMapper videoMapper;

  @Test
  void testMapToVideoResponse() {
    when(videoEntity.getId()).thenReturn(UUID.randomUUID());
    when(videoEntity.getTitle()).thenReturn("Test Video");
    when(videoEntity.getDescription()).thenReturn("Test Description");
    when(videoEntity.getUrl()).thenReturn("https://example.com/test-video");

    VideoResponse videoResponse = videoMapper.mapToVideoResponse(videoEntity);

    assertEquals(videoEntity.getId(), videoResponse.id());
    assertEquals(videoEntity.getTitle(), videoResponse.title());
    assertEquals(videoEntity.getDescription(), videoResponse.description());
  }

  @Test
  void testMapToVideoResponses() {
    List<VideoResponse> videoResponses = videoMapper.mapToVideoResponses(
        List.of(VideoMockData.getSampleVideoEntity()));
    assertEquals(1, videoResponses.size());
  }
}