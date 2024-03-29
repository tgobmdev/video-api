package tgobmdev.videoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.dto.response.VideoResponse;

@Tag(name = "Categorias", description = "Operações relacionadas a categorias")
public interface CategoriaController {

  @Operation(
      summary = "Obter Lista de Categorias",
      description = "Este endpoint tem por objetivo obter a lista de categorias.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Lista de categorias obtida com sucesso.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = VideoResponse.class))
              )
          )
      }
  )
  ResponseEntity<List<CategoriaResponse>> findAllCategories();
}