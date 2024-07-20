package tgobmdev.videoapi.component;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
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
    when(videoRepository.findAllByDeletedAtIsNull()) //
        .thenReturn(Set.of(VideoMock.generateEntity()));

    Set<VideoEntity> result = videoComponent.findAllActiveVideos();

    assertEquals(1, result.size());
  }

  @Test
  void givenVideoIdExists_whenFindActiveVideoById_thenReturnsVideoEntity() {
    UUID videoId = UUID.randomUUID();
    VideoEntity videoEntity = VideoMock.generateEntity();

    when(videoRepository.findByIdAndDeletedAtIsNull(videoId)) //
        .thenReturn(Optional.of(videoEntity));

    Optional<VideoEntity> result = videoComponent.findActiveVideoById(videoId);

    assertEquals(Optional.of(videoEntity), result);
  }

  @Test
  void givenThereAreActiveVideosByTitle_whenFindAllActiveVideosByTitle_thenReturnsListOfVideos() {
    String title = anyString();
    when(videoRepository.findByTitleContainingIgnoreCaseAndDeletedAtIsNull(title)) //
        .thenReturn(Set.of(VideoMock.generateEntity()));

    Set<VideoEntity> result = videoComponent.findAllActiveVideosByTitle(title);

    assertEquals(1, result.size());
  }

  @Test
  void givenVideoEntityExists_whenSaveVideo_thenSavesVideoEntity() {
    VideoEntity videoEntity = VideoMock.generateEntity();

    assertDoesNotThrow(() -> videoComponent.saveVideo(videoEntity));
    assertEquals(Collections.emptySet(), videoEntity.getCategoryEntities());
    verify(videoRepository).save(videoEntity);
  }

  @Test
  void givenVideoIdAndRequestExists_whenEditVideo_thenReturnsUpdatedVideoEntity() {
    UUID videoId = UUID.randomUUID();
    VideoRequest videoRequest = VideoMock.createRequest();
    VideoEntity videoEntity = VideoMock.generateEntity();

    when(videoRepository.findByIdAndDeletedAtIsNull(videoId)) //
        .thenReturn(Optional.of(videoEntity));

    Optional<VideoEntity> result = videoComponent.editVideo(videoId, videoRequest);

    assertEquals(Optional.of(videoEntity), result);
    verify(videoRepository).save(videoEntity);
  }

  @Test
  void givenVideoIdExists_whenDeleteVideo_thenSoftDeletesVideoEntity() {
    UUID videoId = UUID.randomUUID();
    VideoEntity videoEntity = VideoMock.generateEntity();

    when(videoRepository.findByIdAndDeletedAtIsNull(videoId)) //
        .thenReturn(Optional.of(videoEntity));

    assertDoesNotThrow(() -> videoComponent.deleteVideo(videoId));
    verify(videoRepository).save(videoEntity);
    assertEquals(true, videoEntity.getDeleted());
    assertEquals(LocalDateTime.class, videoEntity.getDeletedAt()
        .getClass());
  }

  @Test
  void givenVideoIdNotFound_whenDeleteVideo_thenThrowsApiException() {
    UUID videoId = UUID.randomUUID();
    when(videoRepository.findByIdAndDeletedAtIsNull(videoId)) //
        .thenReturn(Optional.empty());

    assertEquals(ApiException.class,
        assertThrows(ApiException.class, () -> videoComponent.deleteVideo(videoId)).getClass());
  }
}