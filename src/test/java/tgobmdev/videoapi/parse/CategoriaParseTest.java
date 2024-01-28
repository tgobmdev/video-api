package tgobmdev.videoapi.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.entity.CategoriaEntity;
import tgobmdev.videoapi.mock.CategoriaMock;

@ExtendWith(MockitoExtension.class)
class CategoriaParseTest {

  @InjectMocks
  private CategoriaParse categoriaParse;

  @Test
  void toResponse_thenReturnsVideoResponse() {
    CategoriaEntity categoriaEntity = CategoriaMock.generateEntity();

    CategoriaResponse result = categoriaParse.toResponse(categoriaEntity);

    assertEquals(categoriaEntity.getId(), result.id());
    assertEquals(categoriaEntity.getTitle(), result.title());
    assertEquals(categoriaEntity.getColor(), result.color());
  }

  @Test
  void toResponseList_thenReturnsListOfVideoResponses() {
    List<CategoriaEntity> categoriaEntities = List.of(CategoriaMock.generateEntity(),
        CategoriaMock.generateEntity());

    List<CategoriaResponse> result = categoriaParse.toResponseList(categoriaEntities);

    assertEquals(2, result.size());
  }
}