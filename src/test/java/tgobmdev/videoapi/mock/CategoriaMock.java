package tgobmdev.videoapi.mock;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.entity.CategoriaEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoriaMock {

  private static final Faker faker = new Faker();

  public static CategoriaResponse createResponse() {
    return new CategoriaResponse(Long.MAX_VALUE, faker.worldOfWarcraft().hero(),
        faker.lorem().characters());
  }

  public static CategoriaEntity generateEntity() {
    CategoriaEntity categoriaEntity = new CategoriaEntity();
    categoriaEntity.setId(Long.MAX_VALUE);
    categoriaEntity.setTitle(faker.basketball().players());
    categoriaEntity.setColor(faker.lorem().characters());
    return categoriaEntity;
  }
}