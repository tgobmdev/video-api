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
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.exception.ErrorResponse;

@Tag(name = "Categories", description = "Operations related to categories")
public interface CategoryController {

  @Operation(
      summary = "Get List of Categories",
      description = "This endpoint aims to retrieve the list of categories.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "List of categories retrieved successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class))
              )
          )
      }
  )
  ResponseEntity<List<CategoryResponse>> findAllCategories();

  @Operation(
      summary = "Get List of Videos by Category",
      description = "This endpoint aims to retrieve the list of videos associated with a specific category.",
      parameters = {
          @Parameter(
              name = "categoryId",
              description = "Unique ID of the category",
              example = "1",
              required = true,
              in = ParameterIn.PATH
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "List of videos retrieved successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class))
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
              responseCode = "404",
              description = "Category not found.",
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
  ResponseEntity<CategoryResponse> findVideosByCategoryId(Long categoryId);

  @Operation(
      summary = "Get Category by ID",
      description = "Retrieves a specific category by its unique ID.",
      parameters = {
          @Parameter(
              name = "categoryId",
              description = "Unique ID of the category",
              example = "1",
              required = true,
              in = ParameterIn.PATH
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Category retrieved successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CategoryResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Category not found.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  ResponseEntity<CategoryResponse> findCategoryById(Long categoryId);
}