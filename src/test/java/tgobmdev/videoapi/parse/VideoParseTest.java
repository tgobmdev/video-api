package tgobmdev.videoapi.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
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
  void createFromRequest_thenReturnsVideoEntity() {
    VideoRequest videoRequest = VideoMock.generateRequest();

    VideoEntity result = videoParse.createFromRequest(videoRequest);

    assertEquals(videoRequest.title(), result.getTitle());
    assertEquals(videoRequest.description(), result.getDescription());
    assertEquals(videoRequest.url(), result.getUrl());
  }

  @Test
  void toResponse_thenReturnsVideoResponse() {
    VideoEntity videoEntity = VideoMock.generateEntity();

    VideoResponse result = videoParse.toResponse(videoEntity);

    assertEquals(videoEntity.getId(), result.id());
    assertEquals(videoEntity.getTitle(), result.title());
    assertEquals(videoEntity.getDescription(), result.description());
  }

  @Test
  void toResponseList_thenReturnsListOfVideoResponses() {
    List<VideoEntity> videoEntities = List.of(VideoMock.generateEntity(),
        VideoMock.generateEntity());

    List<VideoResponse> result = videoParse.toResponseList(videoEntities);

    assertEquals(2, result.size());
  }
}