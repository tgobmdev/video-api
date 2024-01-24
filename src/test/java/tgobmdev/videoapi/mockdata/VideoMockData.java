package tgobmdev.videoapi.mockdata;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VideoMockData {

  private static final Faker faker = new Faker();

  public static VideoRequest createVideoRequest() {
    return new VideoRequest(faker.dcComics().title(), faker.lorem().characters(),
        faker.internet().url());
  }

  public static VideoResponse getSampleVideoResponse() {
    return new VideoResponse(UUID.randomUUID(), "Video", "Description", "http://example.com/1");
  }

  public static VideoEntity getSampleVideoEntity() {
    VideoEntity videoEntity = new VideoEntity();
    videoEntity.setId(UUID.randomUUID());
    videoEntity.setTitle("Video");
    videoEntity.setDescription("Description");
    videoEntity.setUrl("http://example.com/1");
    return videoEntity;
  }
}