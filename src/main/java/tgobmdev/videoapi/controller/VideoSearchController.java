package tgobmdev.videoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.exception.ErrorResponse;

public interface VideoSearchController {

  @Operation(
      summary = "Obter Lista de Vídeos Ativos por Título",
      description = "Este endpoint tem por objetivo obter a lista de vídeos ativos com base no título.",
      parameters = {
          @Parameter(
              name = "search",
              description = "Título a ser utilizado como critério de busca.",
              required = true,
              in = ParameterIn.QUERY
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Lista de vídeos obtida com sucesso.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = VideoResponse.class))
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
              responseCode = "500",
              description = "Falha ao obter vídeos. Erro interno do servidor.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<List<VideoResponse>> findAllActiveVideosByTitle(String search);
}