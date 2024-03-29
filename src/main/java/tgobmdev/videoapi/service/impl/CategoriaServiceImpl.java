package tgobmdev.videoapi.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.CategoriaComponent;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.dto.response.CategoriaVideoResponse;
import tgobmdev.videoapi.entity.CategoriaEntity;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
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

  @Override
  public CategoriaVideoResponse findVideosByCategoriaId(Long categoriaId) {
    CategoriaEntity categoriaEntity = categoriaComponent.findCategoryById(categoriaId)
        .orElseThrow(() -> new ApiException(404, MessageErrorEnum.CODIGO_2));
    return categoriaParse.toCategoriaVideoResponse(categoriaEntity);
  }
}