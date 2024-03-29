package tgobmdev.videoapi.parse;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.dto.response.CategoriaVideoResponse;
import tgobmdev.videoapi.entity.CategoriaEntity;

@Component
@RequiredArgsConstructor(staticName = "of")
public class CategoriaParse {

  private final VideoParse videoParse;

  public CategoriaResponse toResponse(CategoriaEntity categoria) {
    return new CategoriaResponse(categoria.getId(), categoria.getTitle(), categoria.getColor());
  }

  public CategoriaVideoResponse toCategoriaVideoResponse(CategoriaEntity categoria) {
    return new CategoriaVideoResponse(categoria.getId(), categoria.getTitle(), categoria.getColor(),
        videoParse.toResponseList(categoria.getVideoEntities()));
  }

  public List<CategoriaResponse> toResponseList(List<CategoriaEntity> categorias) {
    return categorias.stream() //
        .map(this::toResponse) //
        .toList();
  }
}