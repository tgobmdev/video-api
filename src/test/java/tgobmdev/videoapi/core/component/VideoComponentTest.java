package tgobmdev.videoapi.core.component;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.core.entity.VideoEntity;
import tgobmdev.videoapi.core.mapper.VideoMapper;
import tgobmdev.videoapi.core.repository.manager.VideoRepositoryManager;
import tgobmdev.videoapi.dto.response.VideoDeleteResponse;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
public class VideoComponentTest {

  @Mock
  private VideoRepositoryManager videoRepositoryManager;

  @Mock
  private VideoMapper videoMapper;

  @InjectMocks
  private VideoComponent videoComponent;

  @Test
  public void testFindAllActiveVideos() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();

    List<VideoEntity> mockVideoEntities = List.of(mockVideoEntity);
    List<VideoResponse> mockVideoResponses = List.of(
        new VideoResponse(mockVideoEntity.getId(), mockVideoEntity.getTitle(),
            mockVideoEntity.getDescription(), mockVideoEntity.getUrl()));

    when(videoRepositoryManager.findAllActiveVideos()).thenReturn(mockVideoEntities);
    when(videoMapper.mapToVideoResponses(mockVideoEntities)).thenReturn(mockVideoResponses);

    List<VideoResponse> result = videoComponent.findAllActiveVideos();

    assertEquals(mockVideoResponses, result);
  }

  @Test
  void testFindActiveVideoById() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();
    when(videoRepositoryManager.findActiveVideoById(any())).thenReturn(
        Optional.of(mockVideoEntity));

    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();
    when(videoMapper.mapToVideoResponse(mockVideoEntity)).thenReturn(mockVideoResponse);

    Optional<VideoResponse> result = videoComponent.findActiveVideoById(UUID.randomUUID());
    assertEquals(Optional.of(mockVideoResponse), result);
  }

  @Test
  void testFindActiveVideoByIdNotFound() {
    when(videoRepositoryManager.findActiveVideoById(any())).thenReturn(Optional.empty());

    Optional<VideoResponse> result = videoComponent.findActiveVideoById(UUID.randomUUID());
    assertEquals(Optional.empty(), result);
  }

  @Test
  public void testDeleteVideo() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();

    when(videoRepositoryManager.findActiveVideoById(any())).thenReturn(
        Optional.of(mockVideoEntity));
    doReturn(VideoMockData.getVideoDeleteResponse()).when(videoMapper)
        .mapToVideoDeleteResponse(mockVideoEntity);

    assertDoesNotThrow(() -> videoComponent.deleteVideo(UUID.randomUUID()));
  }


  @Test
  public void testDeleteVideoNotFound() {
    when(videoRepositoryManager.findActiveVideoById(any())).thenReturn(Optional.empty());

    Optional<VideoDeleteResponse> result = videoComponent.deleteVideo(UUID.randomUUID());
    assertEquals(Optional.empty(), result);
  }
}