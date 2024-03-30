package tgobmdev.videoapi.mock;

import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VideoMock {

  private static final Faker faker = new Faker();

  public static VideoRequest createRequest() {
    return new VideoRequest(faker.dcComics().title(), faker.lorem().characters(),
        faker.internet().url(), Set.of(1L));
  }

  public static VideoResponse createResponse() {
    return new VideoResponse(UUID.randomUUID(), faker.worldOfWarcraft().hero(),
        faker.lorem().characters(), faker.internet().url());
  }

  public static VideoEntity generateEntity() {
    VideoEntity videoEntity = new VideoEntity();
    videoEntity.setId(UUID.randomUUID());
    videoEntity.setTitle(faker.basketball().players());
    videoEntity.setDescription(faker.lorem().characters());
    videoEntity.setUrl(faker.internet().url());
    return videoEntity;
  }
}