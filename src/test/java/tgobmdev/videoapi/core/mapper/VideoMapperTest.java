package tgobmdev.videoapi.core.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.core.entity.VideoEntity;
import tgobmdev.videoapi.dto.VideoDeleteResponse;
import tgobmdev.videoapi.dto.VideoResponse;
import tgobmdev.videoapi.mockdata.VideoMockData;

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
  void testMapToVideoDeleteResponse() {
    when(videoEntity.getId()).thenReturn(UUID.randomUUID());
    when(videoEntity.getTitle()).thenReturn("Test Video");
    when(videoEntity.getDescription()).thenReturn("Test Description");
    when(videoEntity.getUrl()).thenReturn("https://example.com/test-video");
    when(videoEntity.getDeletedAt()).thenReturn(LocalDateTime.now());
    when(videoEntity.getDeleted()).thenReturn(Boolean.TRUE);

    VideoDeleteResponse videoDeleteResponse = videoMapper.mapToVideoDeleteResponse(videoEntity);

    assertEquals(videoEntity.getId(), videoDeleteResponse.id());
    assertEquals(videoEntity.getDeletedAt(), videoDeleteResponse.deletedAt());
    assertEquals(videoEntity.getDeleted(), videoDeleteResponse.deleted());
  }

  @Test
  void testMapToVideoResponses() {
    List<VideoResponse> videoResponses = videoMapper.mapToVideoResponses(
        List.of(VideoMockData.getSampleVideoEntity()));
    assertEquals(1, videoResponses.size());
  }
}