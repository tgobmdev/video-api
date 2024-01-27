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
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.exception.ErrorResponse;

@Tag(name = "Videos", description = "API para gerenciamento de vídeos")
public interface VideoController {

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

  @Operation(
      summary = "Editar um Vídeo Existente",
      description = "Este endpoint tem por objetivo editar um vídeo existente.",
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
              description = "Vídeo editado com sucesso.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = VideoResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Falha ao editar vídeo. Requisição inválida.",
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
              description = "Falha ao editar vídeo. Erro interno do servidor.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<VideoResponse> editVideo(UUID id, VideoRequest videoRequest);

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