package tgobmdev.videoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.exception.ErrorResponse;

public interface VideoDeletionController {

  @Operation(
      summary = "Excluir um Vídeo Existente",
      description = "Este endpoint tem por objetivo excluir um vídeo existente.",
      parameters = {
          @Parameter(
              name = "id",
              description = "ID único do vídeo",
              example = "123e4567-e89b-12d3-a456-426614174001",
              required = true,
              in = ParameterIn.PATH
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Vídeo excluído com sucesso."
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Vídeo não encontrado.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Falha ao excluir vídeo. Erro interno do servidor.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<Void> deleteVideo(UUID id);
}