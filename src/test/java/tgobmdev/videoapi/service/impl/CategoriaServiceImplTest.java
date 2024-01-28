package tgobmdev.videoapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tgobmdev.videoapi.component.CategoriaComponent;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.entity.CategoriaEntity;
import tgobmdev.videoapi.mock.CategoriaMock;
import tgobmdev.videoapi.parse.CategoriaParse;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceImplTest {

  @Mock
  private CategoriaComponent categoriaComponent;

  @Mock
  private CategoriaParse categoriaParse;

  @InjectMocks
  private CategoriaServiceImpl categoriaService;

  @Test
  void givenActiveCategoriesExists_whenFindAllCategories_thenReturnsListOfVideoResponses() {
    List<CategoriaEntity> categoriaEntities = List.of(CategoriaMock.generateEntity(),
        CategoriaMock.generateEntity());
    List<CategoriaResponse> expectedResponses = List.of(CategoriaMock.createResponse(),
        CategoriaMock.createResponse());

    when(categoriaComponent.findAllCategories()) //
        .thenReturn(categoriaEntities);
    when(categoriaParse.toResponseList(categoriaEntities)) //
        .thenReturn(expectedResponses);

    List<CategoriaResponse> result = categoriaService.findAllCategories();

    assertEquals(expectedResponses, result);
    verify(categoriaComponent, times(1)).findAllCategories();
    verify(categoriaParse, times(1)).toResponseList(categoriaEntities);
  }
}