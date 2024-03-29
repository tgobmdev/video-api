package tgobmdev.videoapi.parse;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.dto.response.CategoriaVideoResponse;
import tgobmdev.videoapi.entity.CategoryEntity;

@Component
@RequiredArgsConstructor(staticName = "of")
public class CategoryParse {

  private final VideoParse videoParse;

  public CategoriaResponse toResponse(CategoryEntity categoria) {
    return new CategoriaResponse(categoria.getId(), categoria.getTitle(), categoria.getColor());
  }

  public CategoriaVideoResponse toCategoriaVideoResponse(CategoryEntity categoria) {
    return new CategoriaVideoResponse(categoria.getId(), categoria.getTitle(), categoria.getColor(),
        videoParse.toResponseList(categoria.getVideoEntities()));
  }

  public List<CategoriaResponse> toResponseList(List<CategoryEntity> categorias) {
    return categorias.stream() //
        .map(this::toResponse) //
        .toList();
  }
}