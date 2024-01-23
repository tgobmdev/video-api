package tgobmdev.videoapi.component;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.parse.VideoParse;
import tgobmdev.videoapi.repository.VideoRepository;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.error.exception.ApiException;
import tgobmdev.videoapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
class VideoComponentTest {

  @Mock
  private VideoRepository videoRepository;

  @Mock
  private VideoParse videoParse;

  @InjectMocks
  private VideoComponent videoComponent;

  @Test
  void givenActiveVideosExist_whenFindAllActiveVideos_thenReturnsListOfVideos() {
    List<VideoEntity> mockVideoEntities = List.of(VideoMockData.getSampleVideoEntity());
    List<VideoResponse> mockVideoResponses = List.of(VideoMockData.getSampleVideoResponse());

    when(videoRepository.findAllByDeletedAtIsNull()) //
        .thenReturn(mockVideoEntities);
    when(videoParse.mapToVideoResponses(mockVideoEntities)) //
        .thenReturn(mockVideoResponses);

    List<VideoResponse> result = videoComponent.findAllActiveVideos();
    assertEquals(mockVideoResponses, result);
  }

  @Test
  void givenVideoIdExists_whenFindActiveVideoById_thenReturnsVideo() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();
    UUID videoId = UUID.randomUUID();
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();

    when(videoRepository.findByIdAndDeletedAtIsNull(videoId)) //
        .thenReturn(Optional.of(mockVideoEntity));
    when(videoParse.toVideoResponse(mockVideoEntity)) //
        .thenReturn(mockVideoResponse);

    Optional<VideoResponse> result = videoComponent.findActiveVideoById(videoId);

    assertEquals(Optional.of(mockVideoResponse), result);
  }

  @Test
  void givenVideoIdDoesNotExist_whenFindActiveVideoById_thenReturnsEmptyOptional() {
    UUID nonExistentVideoId = UUID.randomUUID();

    when(videoRepository.findByIdAndDeletedAtIsNull(nonExistentVideoId)) //
        .thenReturn(Optional.empty());

    Optional<VideoResponse> result = videoComponent.findActiveVideoById(nonExistentVideoId);

    assertEquals(Optional.empty(), result);
  }

  @Test
  void givenVideoCreateRequest_whenCreateVideo_thenReturnsCreatedVideo() {
    VideoRequest mockVideoRequest = VideoMockData.getSampleVideoRequest();
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();
    VideoResponse mockVideoResponse = VideoMockData.getSampleVideoResponse();

    when(videoParse.toEntity(mockVideoRequest)) //
        .thenReturn(mockVideoEntity);
    when(videoParse.toVideoResponse(mockVideoEntity)) //
        .thenReturn(mockVideoResponse);

    VideoResponse result = videoComponent.createVideo(mockVideoRequest);

    verify(videoParse).toEntity(mockVideoRequest);
    verify(videoRepository).save(mockVideoEntity);
    verify(videoParse).toVideoResponse(mockVideoEntity);
    assertEquals(mockVideoResponse.hashCode(), result.hashCode());
  }

  @Test
  void givenVideoIdExists_whenDeleteVideo_thenDoesNotThrowException() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();

    when(videoRepository.findByIdAndDeletedAtIsNull(any())) //
        .thenReturn(Optional.of(mockVideoEntity));

    assertDoesNotThrow(() -> videoComponent.deleteVideo(UUID.randomUUID()));
  }

  @Test
  void givenVideoIdDoesNotExist_whenDeleteVideo_thenReturnsEmptyOptional() {
    when(videoRepository.findByIdAndDeletedAtIsNull(any())) //
        .thenReturn(Optional.empty());

    assertThrows(ApiException.class, () -> videoComponent.deleteVideo(UUID.randomUUID()));
  }
}