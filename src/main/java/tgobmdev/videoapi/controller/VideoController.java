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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.exception.ErrorResponse;

@Tag(name = "Videos", description = "API for video management")
public interface VideoController {

  @Operation(
      summary = "Get List of Active Videos",
      description = "This endpoint aims to retrieve the list of active videos.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "List of videos retrieved successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = VideoResponse.class))
              )
          )
      }
  )
  ResponseEntity<List<VideoResponse>> findAllActiveVideos();

  @Operation(
      summary = "Get an Active Video by ID",
      description = "This endpoint aims to retrieve an active video by its ID.",
      parameters = {
          @Parameter(
              name = "videoId",
              description = "Unique ID of the video",
              example = "123e4567-e89b-12d3-a456-426614174001",
              required = true,
              in = ParameterIn.PATH
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Video retrieved successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = VideoResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Failed to retrieve video. Invalid request.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Video not found.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Failed to retrieve video. Internal server error.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<VideoResponse> findActiveVideoById(UUID videoId);

  @Operation(
      summary = "Get List of Active Videos by Title",
      description = "This endpoint aims to retrieve the list of active videos based on title.",
      parameters = {
          @Parameter(
              name = "search",
              description = "Title to be used as search criteria.",
              required = true,
              in = ParameterIn.QUERY
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "List of videos retrieved successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = VideoResponse.class))
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Failed to retrieve videos. Invalid request.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Failed to retrieve videos. Internal server error.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<List<VideoResponse>> findAllActiveVideosByTitle(String search);

  @Operation(
      summary = "Create a New Video",
      description = "This endpoint aims to create a new video.",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Video created successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = VideoResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Failed to create video. Invalid request.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Failed to create video. Internal server error.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<VideoResponse> createVideo(VideoRequest videoRequest);

  @Operation(
      summary = "Edit an Existing Video",
      description = "This endpoint aims to edit an existing video.",
      parameters = {
          @Parameter(
              name = "videoId",
              description = "Unique ID of the video",
              example = "123e4567-e89b-12d3-a456-426614174001",
              required = true,
              in = ParameterIn.PATH
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Video edited successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = VideoResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Failed to edit video. Invalid request.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Video not found.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Failed to edit video. Internal server error.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<VideoResponse> editVideo(UUID videoId, VideoRequest videoRequest);

  @Operation(
      summary = "Delete an Existing Video",
      description = "This endpoint aims to delete an existing video.",
      parameters = {
          @Parameter(
              name = "videoId",
              description = "Unique ID of the video",
              example = "123e4567-e89b-12d3-a456-426614174001",
              required = true,
              in = ParameterIn.PATH
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Video deleted successfully."
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Video not found.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Failed to delete video. Internal server error.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<Void> deleteVideo(UUID videoId);
}