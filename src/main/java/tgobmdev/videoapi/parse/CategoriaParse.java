package tgobmdev.videoapi.parse;

import java.util.List;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.entity.CategoriaEntity;

@Component
public class CategoriaParse {

  public CategoriaResponse toResponse(CategoriaEntity categoria) {
    return new CategoriaResponse(categoria.getId(), categoria.getTitle(), categoria.getColor());
  }

  public List<CategoriaResponse> toResponseList(List<CategoriaEntity> categorias) {
    return categorias.stream() //
        .map(this::toResponse) //
        .toList();
  }
}