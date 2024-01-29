package tgobmdev.videoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.dto.response.VideoResponse;

public interface VideoListController {

  @Operation(
      summary = "Obter Lista de Vídeos Ativos",
      description = "Este endpoint tem por objetivo obter a lista de vídeos ativos.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Lista de vídeos obtida com sucesso.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = VideoResponse.class))
              )
          )
      }
  )
  ResponseEntity<List<VideoResponse>> findAllActiveVideos();
}