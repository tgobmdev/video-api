package tgobmdev.videoapi.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.CategoriaComponent;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.parse.CategoriaParse;
import tgobmdev.videoapi.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

  private final CategoriaComponent categoriaComponent;
  private final CategoriaParse categoriaParse;

  public CategoriaServiceImpl(CategoriaComponent categoriaComponent,
      CategoriaParse categoriaParse) {
    this.categoriaComponent = categoriaComponent;
    this.categoriaParse = categoriaParse;
  }

  @Override
  public List<CategoriaResponse> findAllCategories() {
    return categoriaParse.toResponseList(categoriaComponent.findAllCategories());
  }
}