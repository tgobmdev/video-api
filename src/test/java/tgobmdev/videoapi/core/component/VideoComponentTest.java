package tgobmdev.videoapi.core.component;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
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
import tgobmdev.videoapi.dto.request.VideoCreateRequest;
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
  void givenActiveVideosExist_whenFindAllActiveVideos_thenReturnsListOfVideos() {
    List<VideoEntity> mockVideoEntities = List.of(VideoMockData.getSampleVideoEntity());
    List<VideoResponse> mockVideoResponses = List.of(VideoMockData.getSampleVideoResponse());

    when(videoRepositoryManager.findAllActiveVideos()) //
        .thenReturn(mockVideoEntities);
    when(videoMapper.mapToVideoResponses(mockVideoEntities)) //
        .thenReturn(mockVideoResponses);

    List<VideoResponse> result = videoComponent.findAllActiveVideos();
    assertEquals(mockVideoResponses, result);
  }

  @Test
  void givenVideoIdExists_whenFindActiveVideoById_thenReturnsVideo() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();
    UUID videoId = UUID.randomUUID();
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();

    when(videoRepositoryManager.findActiveVideoById(videoId)) //
        .thenReturn(Optional.of(mockVideoEntity));
    when(videoMapper.mapToVideoResponse(mockVideoEntity)) //
        .thenReturn(mockVideoResponse);

    Optional<VideoResponse> result = videoComponent.findActiveVideoById(videoId);

    assertEquals(Optional.of(mockVideoResponse), result);
  }

  @Test
  void givenVideoIdDoesNotExist_whenFindActiveVideoById_thenReturnsEmptyOptional() {
    UUID nonExistentVideoId = UUID.randomUUID();

    when(videoRepositoryManager.findActiveVideoById(nonExistentVideoId)) //
        .thenReturn(Optional.empty());

    Optional<VideoResponse> result = videoComponent.findActiveVideoById(nonExistentVideoId);

    assertEquals(Optional.empty(), result);
  }

  @Test
  void givenVideoCreateRequest_whenCreateVideo_thenReturnsCreatedVideo() {
    VideoCreateRequest mockVideoCreateRequest = VideoMockData.getVideoCreateRequest();
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();

    when(videoMapper.mapToEntity(mockVideoCreateRequest)) //
        .thenReturn(mockVideoEntity);
    when(videoMapper.mapToVideoResponse(mockVideoEntity)) //
        .thenReturn(mockVideoResponse);

    VideoResponse result = videoComponent.createVideo(mockVideoCreateRequest);

    verify(videoMapper).mapToEntity(mockVideoCreateRequest);
    verify(videoRepositoryManager).saveVideo(mockVideoEntity);
    verify(videoMapper).mapToVideoResponse(mockVideoEntity);
    assertEquals(mockVideoResponse.hashCode(), result.hashCode());
  }

  @Test
  void givenVideoIdExists_whenDeleteVideo_thenDoesNotThrowException() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();

    when(videoRepositoryManager.findActiveVideoById(any())) //
        .thenReturn(Optional.of(mockVideoEntity));
    doReturn(VideoMockData.getVideoDeleteResponse()) //
        .when(videoMapper) //
        .mapToVideoDeleteResponse(mockVideoEntity);

    assertDoesNotThrow(() -> videoComponent.deleteVideo(UUID.randomUUID()));
  }

  @Test
  void givenVideoIdDoesNotExist_whenDeleteVideo_thenReturnsEmptyOptional() {
    when(videoRepositoryManager.findActiveVideoById(any())) //
        .thenReturn(Optional.empty());

    Optional<VideoDeleteResponse> result = videoComponent.deleteVideo(UUID.randomUUID());

    assertEquals(Optional.empty(), result);
  }
}