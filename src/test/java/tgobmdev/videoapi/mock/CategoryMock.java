package tgobmdev.videoapi.mock;

import java.util.Collections;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.entity.CategoryEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMock {

  private static final Faker faker = new Faker();

  public static CategoryResponse createResponse() {
    return new CategoryResponse(Long.MAX_VALUE, faker.worldOfWarcraft()
        .hero(),
        faker.lorem()
            .characters(), Collections.emptyList());
  }

  public static CategoryEntity generateEntity() {
    CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.setId(Long.MAX_VALUE);
    categoryEntity.setTitle(faker.basketball()
        .players());
    categoryEntity.setColor(faker.lorem()
        .characters());
    categoryEntity.setVideoEntities(Collections.emptySet());
    return categoryEntity;
  }
}