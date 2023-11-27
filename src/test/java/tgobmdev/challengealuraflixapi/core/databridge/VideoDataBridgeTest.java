package tgobmdev.challengealuraflixapi.core.databridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import tgobmdev.challengealuraflixapi.core.entity.VideoEntity;
import tgobmdev.challengealuraflixapi.core.mapper.VideoMapper;
import tgobmdev.challengealuraflixapi.core.repository.VideoRepository;
import tgobmdev.challengealuraflixapi.dto.VideoResponse;
import tgobmdev.challengealuraflixapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
public class VideoDataBridgeTest {

  @Mock
  private VideoRepository videoRepository;

  @Mock
  private VideoMapper videoMapper;

  @InjectMocks
  private VideoDataBridge videoDataBridge;

  @Test
  public void testFindAllVideos() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();

    List<VideoEntity> mockVideoEntities = List.of(mockVideoEntity);
    List<VideoResponse> mockVideoResponses = List.of(
        new VideoResponse(mockVideoEntity.getId(), mockVideoEntity.getTitle(),
            mockVideoEntity.getDescription(), mockVideoEntity.getUrl()));

    when(videoRepository.findAll()).thenReturn(mockVideoEntities);
    when(videoMapper.mapToVideoResponses(mockVideoEntities)).thenReturn(mockVideoResponses);

    List<VideoResponse> result = videoDataBridge.findAllVideos();

    assertEquals(mockVideoResponses, result);
  }

  @Test
  void testFindVideoById() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();
    when(videoRepository.findById(any())).thenReturn(Optional.of(mockVideoEntity));

    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();
    when(videoMapper.mapToVideoResponse(mockVideoEntity)).thenReturn(mockVideoResponse);

    Optional<VideoResponse> result = videoDataBridge.findVideoById(UUID.randomUUID());
    assertEquals(Optional.of(mockVideoResponse), result);
  }

  @Test
  void testFindVideoByIdNotFound() {
    when(videoRepository.findById(any())).thenReturn(Optional.empty());

    Optional<VideoResponse> result = videoDataBridge.findVideoById(UUID.randomUUID());
    assertEquals(Optional.empty(), result);
  }
}