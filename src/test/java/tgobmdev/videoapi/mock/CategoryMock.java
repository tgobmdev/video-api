package tgobmdev.videoapi.mock;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.entity.CategoryEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMock {

  private static final Faker faker = new Faker();

  public static CategoryResponse createResponse() {
    return new CategoryResponse(Long.MAX_VALUE, faker.worldOfWarcraft().hero(),
        faker.lorem().characters());
  }

  public static CategoryEntity generateEntity() {
    CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.setId(Long.MAX_VALUE);
    categoryEntity.setTitle(faker.basketball().players());
    categoryEntity.setColor(faker.lorem().characters());
    return categoryEntity;
  }
}