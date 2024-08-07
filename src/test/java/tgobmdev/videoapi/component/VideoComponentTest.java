package tgobmdev.videoapi.component;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.dto.request.VideoFilter;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.mock.VideoMock;
import tgobmdev.videoapi.repository.VideoRepository;

@ExtendWith(MockitoExtension.class)
class VideoComponentTest {

  @Mock
  private VideoRepository videoRepository;

  @InjectMocks
  private VideoComponent videoComponent;

  @Test
  void givenActiveVideosExists_whenFindAllActiveVideos_thenReturnsListOfVideos() {
    VideoFilter filter = VideoMock.createFilter();

    when(videoRepository.findVideosByFilter(filter)).thenReturn(Set.of(VideoMock.createEntity()));
    Set<VideoEntity> result = videoComponent.findAllActiveVideos(filter);

    assertEquals(1, result.size());
  }

  @Test
  void givenVideoIdExists_whenFindActiveVideoById_thenReturnsVideo() {
    UUID videoId = UUID.randomUUID();
    VideoEntity videoEntity = VideoMock.createEntity();

    when(videoRepository.findByIdAndDeletedAtIsNull(videoId)).thenReturn(Optional.of(videoEntity));
    Optional<VideoEntity> result = videoComponent.findActiveVideoById(videoId);

    assertEquals(Optional.of(videoEntity), result);
  }

  @Test
  void givenThereAreActiveVideosByTitle_whenFindAllActiveVideos_thenReturnsListOfVideos() {
    VideoFilter filter = VideoMock.createFilter("Test");

    when(videoRepository.findVideosByFilter(filter)).thenReturn(Set.of(VideoMock.createEntity()));
    Set<VideoEntity> result = videoComponent.findAllActiveVideos(filter);

    assertEquals(1, result.size());
  }

  @Test
  void givenVideoExists_whenSaveVideo_thenSavesVideo() {
    VideoEntity videoEntity = VideoMock.createEntity();

    assertDoesNotThrow(() -> videoComponent.saveVideo(videoEntity));

    assertEquals(Collections.emptySet(), videoEntity.getCategoryEntities());
    verify(videoRepository).save(videoEntity);
  }

  @Test
  void givenVideoIdAndRequestExists_whenEditVideo_thenReturnsVideoUpdated() {
    UUID videoId = UUID.randomUUID();
    VideoRequest videoRequest = VideoMock.createRequest();
    VideoEntity videoEntity = VideoMock.createEntity();

    when(videoRepository.findByIdAndDeletedAtIsNull(videoId)).thenReturn(Optional.of(videoEntity));
    Optional<VideoEntity> result = videoComponent.editVideo(videoId, videoRequest);

    assertEquals(Optional.of(videoEntity), result);
    verify(videoRepository).save(videoEntity);
  }

  @Test
  void givenVideoIdExists_whenDeleteVideo_thenSoftDeletesVideo() {
    UUID videoId = UUID.randomUUID();
    VideoEntity videoEntity = VideoMock.createEntity();

    when(videoRepository.findByIdAndDeletedAtIsNull(videoId)).thenReturn(Optional.of(videoEntity));

    assertDoesNotThrow(() -> videoComponent.deleteVideo(videoId));
    verify(videoRepository).save(videoEntity);
    assertEquals(true, videoEntity.getDeleted());
    assertEquals(LocalDateTime.class, videoEntity.getDeletedAt()
        .getClass());
  }

  @Test
  void givenVideoIdNotFound_whenDeleteVideo_thenThrowsApiException() {
    UUID videoId = UUID.randomUUID();
    when(videoRepository.findByIdAndDeletedAtIsNull(videoId)).thenReturn(Optional.empty());

    assertEquals(ApiException.class,
        assertThrows(ApiException.class, () -> videoComponent.deleteVideo(videoId)).getClass());
  }
}