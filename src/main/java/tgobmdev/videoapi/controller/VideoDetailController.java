package tgobmdev.videoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.exception.ErrorResponse;

public interface VideoDetailController {

  @Operation(
      summary = "Obter um Vídeo Ativo por ID",
      description = "Este endpoint tem por objetivo obter um vídeo ativo pelo seu ID.",
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
              responseCode = "200",
              description = "Vídeo obtido com sucesso.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = VideoResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Falha ao obter vídeo. Requisição inválida.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
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
              description = "Falha ao obter vídeo. Erro interno do servidor.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<VideoResponse> findActiveVideoById(UUID id, HttpHeaders httpHeaders);
}