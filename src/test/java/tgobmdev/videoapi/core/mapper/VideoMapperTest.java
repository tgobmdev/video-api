package tgobmdev.videoapi.core.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.core.entity.VideoEntity;
import tgobmdev.videoapi.dto.response.VideoResponse;

@ExtendWith(MockitoExtension.class)
class VideoMapperTest {

  @Mock
  private VideoEntity videoEntity;

  @InjectMocks
  private VideoMapper videoMapper;

  @BeforeEach
  void setUp() {
    when(videoEntity.getId()).thenReturn(UUID.randomUUID());
    when(videoEntity.getTitle()).thenReturn("Test Video");
    when(videoEntity.getDescription()).thenReturn("Test Description");
    when(videoEntity.getUrl()).thenReturn("https://example.com/test-video");
  }

  @Test
  void mapToVideoResponse_ShouldMapVideoEntityToVideoResponse() {
    VideoResponse videoResponse = videoMapper.mapToVideoResponse(videoEntity);

    assertEquals(videoEntity.getId(), videoResponse.id());
    assertEquals(videoEntity.getTitle(), videoResponse.title());
    assertEquals(videoEntity.getDescription(), videoResponse.description());
  }

  @Test
  void mapToVideoResponses_ShouldMapListOfVideoEntityToVideoResponses() {
    List<VideoResponse> videoResponses = videoMapper.mapToVideoResponses(List.of(videoEntity));

    VideoResponse videoResponse = videoResponses.getFirst();
    assertEquals(videoEntity.getId(), videoResponse.id());
    assertEquals(videoEntity.getTitle(), videoResponse.title());
    assertEquals(videoEntity.getDescription(), videoResponse.description());
  }
}