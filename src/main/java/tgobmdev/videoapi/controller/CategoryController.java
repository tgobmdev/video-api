package tgobmdev.videoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.dto.response.CategoriaVideoResponse;
import tgobmdev.videoapi.exception.ErrorResponse;

@Tag(name = "Categorias", description = "Operações relacionadas a categorias")
public interface CategoryController {

  @Operation(
      summary = "Obter Lista de Categorias",
      description = "Este endpoint tem por objetivo obter a lista de categorias.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Lista de categorias obtida com sucesso.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = CategoriaResponse.class))
              )
          )
      }
  )
  ResponseEntity<List<CategoriaResponse>> findAllCategories();

  @Operation(
      summary = "Obter Lista de Vídeos por Categoria",
      description = "Este endpoint tem por objetivo obter a lista de vídeos associados a uma categoria específica.",
      parameters = {
          @Parameter(
              name = "idCategoria",
              description = "ID único da categoria",
              example = "1",
              required = true,
              in = ParameterIn.PATH
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Lista de vídeos obtida com sucesso.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = CategoriaVideoResponse.class))
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Falha ao obter vídeos. Requisição inválida.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Categoria não encontrada.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Falha ao obter vídeos. Erro interno do servidor.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<CategoriaVideoResponse> findVideosByCategoryId(Long categoryId);
}