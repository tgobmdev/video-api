package tgobmdev.videoapi.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.mock.CategoryMock;

@ExtendWith(MockitoExtension.class)
class CategoryParseTest {

  @Mock
  private VideoParse videoParse;

  @InjectMocks
  private CategoryParse categoryParse;

  @Test
  void parseToCategoryResponse_thenReturnsCategoryResponse() {
    CategoryEntity categoryEntity = CategoryMock.generateEntity();

    CategoryResponse result = categoryParse.parseToCategoryResponse(categoryEntity, Boolean.FALSE);

    assertEquals(categoryEntity.getId(), result.id());
    assertEquals(categoryEntity.getTitle(), result.title());
    assertEquals(categoryEntity.getColor(), result.color());
  }

  @Test
  void parseToCategoryResponses_thenReturnsListOfCategoryResponse() {
    List<CategoryEntity> categoryEntities = List.of(CategoryMock.generateEntity(),
        CategoryMock.generateEntity());

    List<CategoryResponse> result = categoryParse.parseToCategoryResponses(categoryEntities,
        Boolean.FALSE);

    assertEquals(2, result.size());
  }
}