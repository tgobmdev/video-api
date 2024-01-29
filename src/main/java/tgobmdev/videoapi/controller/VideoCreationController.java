package tgobmdev.videoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.exception.ErrorResponse;

public interface VideoCreationController {

  @Operation(
      summary = "Criar um Novo Vídeo",
      description = "Este endpoint tem por objetivo criar um novo vídeo.",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Vídeo criado com sucesso.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = VideoResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Falha ao criar vídeo. Requisição inválida.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Falha ao criar vídeo. Erro interno do servidor.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<VideoResponse> createVideo(VideoRequest videoRequest);
}