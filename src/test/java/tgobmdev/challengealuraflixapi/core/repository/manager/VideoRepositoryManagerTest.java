package tgobmdev.challengealuraflixapi.core.repository.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.challengealuraflixapi.core.entity.VideoEntity;
import tgobmdev.challengealuraflixapi.core.repository.VideoRepository;
import tgobmdev.challengealuraflixapi.mockdata.VideoMockData;

@ExtendWith(MockitoExtension.class)
public class VideoRepositoryManagerTest {

  @Mock
  private VideoRepository videoRepository;

  @InjectMocks
  private VideoRepositoryManager videoRepositoryManager;

  @Test
  void testSaveVideo() {
    VideoEntity mockVideoEntity = VideoMockData.getSampleVideoEntity();

    when(videoRepository.save(Mockito.any())).thenReturn(mockVideoEntity);
    videoRepositoryManager.saveVideo(mockVideoEntity);

    assertEquals("Description", mockVideoEntity.getDescription());
    assertEquals("http://example.com/1", mockVideoEntity.getUrl());
  }

  @Test
  void testFindActiveVideoById() {
    Optional<VideoEntity> mockVideoEntity = Optional.of(VideoMockData.getSampleVideoEntity());

    when(videoRepository.findByIdAndDeletedAtIsNull(Mockito.any())).thenReturn(mockVideoEntity);
    Optional<VideoEntity> result = videoRepositoryManager.findActiveVideoById(UUID.randomUUID());

    assertTrue(result.isPresent());
    assertSame(mockVideoEntity, result);
  }

  @Test
  void testFindAllActiveVideos() {
    List<VideoEntity> mockVideoEntities = new ArrayList<>();

    when(videoRepository.findAllByDeletedAtIsNull()).thenReturn(mockVideoEntities);
    List<VideoEntity> result = videoRepositoryManager.findAllActiveVideos();

    assertTrue(result.isEmpty());
    assertSame(mockVideoEntities, result);
  }
}