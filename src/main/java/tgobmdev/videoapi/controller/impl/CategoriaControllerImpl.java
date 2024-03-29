package tgobmdev.videoapi.controller.impl;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.CategoriaController;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.CategoriaListService;
import tgobmdev.videoapi.service.VideoCategoriaService;

@Log4j2
@RestController
@RequestMapping(value = "categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaControllerImpl implements CategoriaController {

  private final CategoriaListService categoriaListService;
  private final VideoCategoriaService videoCategoriaService;

  public CategoriaControllerImpl(CategoriaListService categoriaListService,
      VideoCategoriaService videoCategoriaService) {
    this.categoriaListService = categoriaListService;
    this.videoCategoriaService = videoCategoriaService;
  }

  @Override
  @GetMapping(value = "/list")
  public ResponseEntity<List<CategoriaResponse>> findAllCategories() {
    log.info("Requisição [GET] recebida em [/categorias/list]");
    List<CategoriaResponse> categoriaResponses = categoriaListService.findAllCategories();
    log.info("Requisição [GET] finalizada em [/categorias/list]");
    return ResponseEntity.ok(categoriaResponses);
  }

  @GetMapping("/{idCategoria}/videos")
  public ResponseEntity<List<VideoResponse>> findByCategoriaEntitiesId(
      @PathVariable Long idCategoria) {
    log.info("Requisição [DELETE] recebida em [/categorias/{}/videos]", idCategoria);
    List<VideoResponse> videoResponses = videoCategoriaService.findByCategoriaEntitiesId(
        idCategoria);
    log.info("Requisição [DELETE] finalizada em [/categorias/{}/videos]", idCategoria);
    return ResponseEntity.ok(videoResponses);
  }
}