package tgobmdev.videoapi.controller.impl;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.VideoCategoriaController;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.CategoriaVideoService;

@Log4j2
@RestController
public class VideoCategoriaControllerImpl implements VideoCategoriaController {

  private final CategoriaVideoService categoriaVideoService;

  public VideoCategoriaControllerImpl(CategoriaVideoService categoriaVideoService) {
    this.categoriaVideoService = categoriaVideoService;
  }

  @Override
  @GetMapping("/categorias/{idCategoria}/videos")
  public ResponseEntity<List<VideoResponse>> findByCategoriaEntitiesId(
      @PathVariable Long idCategoria) {
    log.info("Requisição [DELETE] recebida em [/categorias/{}/videos]", idCategoria);
    List<VideoResponse> videoResponses = categoriaVideoService.findByCategoriaEntitiesId(
        idCategoria);
    log.info("Requisição [DELETE] finalizada em [/categorias/{}/videos]", idCategoria);
    return ResponseEntity.ok(videoResponses);
  }
}