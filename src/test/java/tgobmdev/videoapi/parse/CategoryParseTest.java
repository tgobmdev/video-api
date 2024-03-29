package tgobmdev.videoapi.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.mock.CategoriaMock;

@ExtendWith(MockitoExtension.class)
class CategoryParseTest {

  @InjectMocks
  private CategoryParse categoryParse;

  @Test
  void toResponse_thenReturnsVideoResponse() {
    CategoryEntity categoryEntity = CategoriaMock.generateEntity();

    CategoriaResponse result = categoryParse.toResponse(categoryEntity);

    assertEquals(categoryEntity.getId(), result.id());
    assertEquals(categoryEntity.getTitle(), result.title());
    assertEquals(categoryEntity.getColor(), result.color());
  }

  @Test
  void toResponseList_thenReturnsListOfVideoResponses() {
    List<CategoryEntity> categoriaEntities = List.of(CategoriaMock.generateEntity(),
        CategoriaMock.generateEntity());

    List<CategoriaResponse> result = categoryParse.toResponseList(categoriaEntities);

    assertEquals(2, result.size());
  }
}