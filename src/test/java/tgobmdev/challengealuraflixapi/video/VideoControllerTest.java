package tgobmdev.challengealuraflixapi.video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tgobmdev.challengealuraflixapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
public class VideoControllerTest {

  @Mock
  private VideoService videoService;

  @InjectMocks
  private VideoController videoController;

  @Test
  void testGetVideos() {
    List<VideoResponse> mockVideoResponses = List.of(VideoMockData.getSampleVideoResponse());

    when(videoService.getVideos()).thenReturn(mockVideoResponses);
    ResponseEntity<List<VideoResponse>> responseEntity = videoController.getVideos();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockVideoResponses, responseEntity.getBody());
  }
}