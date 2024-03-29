package tgobmdev.videoapi.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.mock.CategoryMock;
import tgobmdev.videoapi.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryComponentTest {

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private CategoryComponent categoryComponent;

  @Test
  void givenActiveCategoriesExists_whenFindAllCategories_thenReturnsListOfVideos() {
    when(categoryRepository.findAll()) //
        .thenReturn(List.of(CategoryMock.generateEntity()));

    List<CategoryEntity> result = categoryComponent.findAllCategories();

    assertEquals(1, result.size());
  }
}