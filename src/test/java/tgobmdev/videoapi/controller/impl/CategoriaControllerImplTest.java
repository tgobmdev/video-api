package tgobmdev.videoapi.controller.impl;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.mock.CategoriaMock;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerImplTest {

  @Mock
  private CategoriaService categoriaService;

  @InjectMocks
  private CategoriaControllerImpl categoriaController;

  @Test
  void givenActiveCategoriesExists_whenFindAllCategories_thenReturnsListOfVideoResponses() {
    List<CategoriaResponse> expectedResponses = List.of(CategoriaMock.createResponse(),
        CategoriaMock.createResponse());

    when(categoriaService.findAllCategories()) //
        .thenReturn(expectedResponses);

    ResponseEntity<List<CategoriaResponse>> responseEntity = categoriaController.findAllCategories();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expectedResponses, responseEntity.getBody());
    verify(categoriaService, times(1)).findAllCategories();
  }
}