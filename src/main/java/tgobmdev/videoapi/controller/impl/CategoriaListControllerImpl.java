package tgobmdev.videoapi.controller.impl;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.CategoriaListController;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.service.CategoriaListService;

@Log4j2
@RestController
@RequestMapping(value = "categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaListControllerImpl implements CategoriaListController {

  private final CategoriaListService categoriaListService;

  public CategoriaListControllerImpl(CategoriaListService categoriaListService) {
    this.categoriaListService = categoriaListService;
  }

  @GetMapping
  public ResponseEntity<List<CategoriaResponse>> findAllCategories() {
    log.info("Requisição [GET] recebida em [/categorias]");
    List<CategoriaResponse> categoriaResponses = categoriaListService.findAllCategories();
    log.info("Requisição [GET] finalizada em [/categorias]");
    return ResponseEntity.ok(categoriaResponses);
  }
}