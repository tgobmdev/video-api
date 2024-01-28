package tgobmdev.videoapi.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.entity.CategoriaEntity;
import tgobmdev.videoapi.mock.CategoriaMock;
import tgobmdev.videoapi.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
class CategoriaComponentTest {

  @Mock
  private CategoriaRepository categoriaRepository;

  @InjectMocks
  private CategoriaComponent categoriaComponent;

  @Test
  void givenActiveCategoriesExists_whenFindAllCategories_thenReturnsListOfVideos() {
    when(categoriaRepository.findAll()) //
        .thenReturn(List.of(CategoriaMock.generateEntity()));

    List<CategoriaEntity> result = categoriaComponent.findAllCategories();

    assertEquals(1, result.size());
  }
}