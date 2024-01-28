package tgobmdev.videoapi.mock;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.entity.CategoriaEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoriaMock {

  private static final Faker faker = new Faker();

  public static CategoriaResponse createResponse() {
    return new CategoriaResponse(UUID.randomUUID(), faker.worldOfWarcraft().hero(),
        faker.lorem().characters());
  }

  public static CategoriaEntity generateEntity() {
    CategoriaEntity categoriaEntity = new CategoriaEntity();
    categoriaEntity.setId(UUID.randomUUID());
    categoriaEntity.setTitle(faker.basketball().players());
    categoriaEntity.setColor(faker.lorem().characters());
    return categoriaEntity;
  }
}