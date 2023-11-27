package tgobmdev.challengealuraflixapi.video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.challengealuraflixapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

  @Mock
  private VideoRepository videoRepository;

  @Mock
  private VideoMapper videoMapper;

  @InjectMocks
  private VideoService videoService;

  @Test
  public void testGetVideos() {
    VideoEntity videoEntity = VideoMockData.getSampleVideoEntity();

    List<VideoEntity> mockVideoEntities = List.of(videoEntity);
    List<VideoResponse> mockVideoResponses = List.of(
        new VideoResponse(videoEntity.getId(), videoEntity.getTitle(), videoEntity.getDescription(),
            videoEntity.getUrl()));

    when(videoRepository.findAll()).thenReturn(mockVideoEntities);
    when(videoMapper.mapToVideoResponses(mockVideoEntities)).thenReturn(mockVideoResponses);

    List<VideoResponse> result = videoService.getVideos();

    assertEquals(mockVideoResponses, result);
  }
}