package tgobmdev.videoapi.parse;

import java.util.List;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.entity.CategoryEntity;

@Component
public class CategoryParse {

  private final VideoParse videoParse;

  public CategoryParse(VideoParse videoParse) {
    this.videoParse = videoParse;
  }

  public CategoryResponse toResponse(CategoryEntity category) {
    return new CategoryResponse(category.getId(), category.getTitle(), category.getColor(),
        videoParse.toResponseList(category.getVideoEntities()));
  }

  public List<CategoryResponse> toResponseList(List<CategoryEntity> categories) {
    return categories.stream() //
        .map(this::toResponse) //
        .toList();
  }
}