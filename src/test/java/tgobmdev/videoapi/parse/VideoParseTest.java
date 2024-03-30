package tgobmdev.videoapi.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;
import tgobmdev.videoapi.mock.VideoMock;

@ExtendWith(MockitoExtension.class)
class VideoParseTest {

  @InjectMocks
  private VideoParse videoParse;

  @Test
  void parseToVideoEntity_thenReturnsVideoEntity() {
    VideoRequest videoRequest = VideoMock.createRequest();

    VideoEntity result = videoParse.parseToVideoEntity(videoRequest);

    assertEquals(videoRequest.title(), result.getTitle());
    assertEquals(videoRequest.description(), result.getDescription());
    assertEquals(videoRequest.url(), result.getUrl());
  }

  @Test
  void parseToVideoResponse_thenReturnsVideoResponse() {
    VideoEntity videoEntity = VideoMock.generateEntity();

    VideoResponse result = videoParse.parseToVideoResponse(videoEntity);

    assertEquals(videoEntity.getId(), result.id());
    assertEquals(videoEntity.getTitle(), result.title());
    assertEquals(videoEntity.getDescription(), result.description());
  }

  @Test
  void parseToVideoResponses_thenReturnsListOfVideoResponse() {
    Set<VideoEntity> videoEntities = Set.of(VideoMock.generateEntity(), VideoMock.generateEntity());

    List<VideoResponse> result = videoParse.parseToVideoResponses(videoEntities);

    assertEquals(2, result.size());
  }
}