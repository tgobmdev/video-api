package tgobmdev.videoapi.parse;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.dto.response.CategoryVideoResponse;
import tgobmdev.videoapi.entity.CategoryEntity;

@Component
@RequiredArgsConstructor(staticName = "of")
public class CategoryParse {

  private final VideoParse videoParse;

  public CategoryResponse toResponse(CategoryEntity categoria) {
    return new CategoryResponse(categoria.getId(), categoria.getTitle(), categoria.getColor());
  }

  public CategoryVideoResponse toCategoriaVideoResponse(CategoryEntity categoria) {
    return new CategoryVideoResponse(categoria.getId(), categoria.getTitle(), categoria.getColor(),
        videoParse.toResponseList(categoria.getVideoEntities()));
  }

  public List<CategoryResponse> toResponseList(List<CategoryEntity> categorias) {
    return categorias.stream() //
        .map(this::toResponse) //
        .toList();
  }
}