package tgobmdev.videoapi.parse;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.CategoryEntity;

@Component
public class CategoryParse {

  private final VideoParse videoParse;

  public CategoryParse(VideoParse videoParse) {
    this.videoParse = videoParse;
  }

  public CategoryResponse parseToCategoryResponse(CategoryEntity category, boolean includeVideos) {
    List<VideoResponse> videos =
        includeVideos ? videoParse.parseToVideoResponses(category.getVideoEntities())
            : Collections.emptyList();
    return new CategoryResponse(category.getId(), category.getTitle(), category.getColor(), videos);
  }

  public List<CategoryResponse> parseToCategoryResponses(List<CategoryEntity> categories,
      boolean includeVideos) {
    return categories.stream() //
        .map(categoryEntity -> parseToCategoryResponse(categoryEntity, includeVideos)) //
        .toList();
  }
}