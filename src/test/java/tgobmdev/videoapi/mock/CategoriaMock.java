package tgobmdev.videoapi.mock;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.entity.CategoryEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoriaMock {

  private static final Faker faker = new Faker();

  public static CategoriaResponse createResponse() {
    return new CategoriaResponse(Long.MAX_VALUE, faker.worldOfWarcraft().hero(),
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