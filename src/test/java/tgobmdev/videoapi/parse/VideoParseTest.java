package tgobmdev.videoapi.parse;

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
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.parse.VideoParse;

@ExtendWith(MockitoExtension.class)
class VideoParseTest {

  @Mock
  private VideoEntity videoEntity;

  @InjectMocks
  private VideoParse videoParse;

  @BeforeEach
  void setUp() {
    when(videoEntity.getId()).thenReturn(UUID.randomUUID());
    when(videoEntity.getTitle()).thenReturn("Test Video");
    when(videoEntity.getDescription()).thenReturn("Test Description");
    when(videoEntity.getUrl()).thenReturn("https://example.com/test-video");
  }

  @Test
  void mapToVideoResponse_ShouldMapVideoEntityToVideoResponse() {
    VideoResponse videoResponse = videoParse.toVideoResponse(videoEntity);

    assertEquals(videoEntity.getId(), videoResponse.id());
    assertEquals(videoEntity.getTitle(), videoResponse.title());
    assertEquals(videoEntity.getDescription(), videoResponse.description());
  }

  @Test
  void mapToVideoResponses_ShouldMapListOfVideoEntityToVideoResponses() {
    List<VideoResponse> videoResponses = videoParse.mapToVideoResponses(List.of(videoEntity));

    VideoResponse videoResponse = videoResponses.getFirst();
    assertEquals(videoEntity.getId(), videoResponse.id());
    assertEquals(videoEntity.getTitle(), videoResponse.title());
    assertEquals(videoEntity.getDescription(), videoResponse.description());
  }
}