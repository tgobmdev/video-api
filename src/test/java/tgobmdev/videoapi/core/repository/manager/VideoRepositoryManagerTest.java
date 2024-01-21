package tgobmdev.videoapi.core.repository.manager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.core.entity.VideoEntity;
import tgobmdev.videoapi.core.repository.VideoRepository;
import tgobmdev.videoapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
class VideoRepositoryManagerTest {

  @Mock
  private VideoRepository videoRepository;

  @InjectMocks
  private VideoRepositoryManager videoRepositoryManager;

  private VideoEntity mockVideoEntity;

  @BeforeEach
  void setUp() {
    mockVideoEntity = VideoMockData.getSampleVideoEntity();
  }

  @Test
  void givenVideoEntity_whenSaveVideo_thenNoExceptions() {
    when(videoRepository.save(Mockito.any())).thenReturn(mockVideoEntity);

    assertDoesNotThrow(() -> videoRepositoryManager.saveVideo(mockVideoEntity));
  }

  @Test
  void givenExistingVideoEntity_whenFindActiveVideoById_thenReturnOptionalEntity() {
    Optional<VideoEntity> expectedEntity = Optional.of(mockVideoEntity);
    when(videoRepository.findByIdAndDeletedAtIsNull(Mockito.any())).thenReturn(expectedEntity);

    Optional<VideoEntity> result = videoRepositoryManager.findActiveVideoById(UUID.randomUUID());

    assertTrue(result.isPresent());
    assertEquals(expectedEntity, result);
  }

  @Test
  void givenNoDeletedVideos_whenFindAllActiveVideos_thenReturnEmptyList() {
    List<VideoEntity> expectedEntities = new ArrayList<>();
    when(videoRepository.findAllByDeletedAtIsNull()).thenReturn(expectedEntities);

    List<VideoEntity> result = videoRepositoryManager.findAllActiveVideos();

    assertTrue(result.isEmpty());
    assertEquals(expectedEntities, result);
  }
}