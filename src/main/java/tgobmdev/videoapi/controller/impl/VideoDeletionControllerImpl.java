package tgobmdev.videoapi.controller.impl;

import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.VideoDeletionController;
import tgobmdev.videoapi.service.VideoDeletionService;

@Log4j2
@RestController
@RequestMapping(value = "videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoDeletionControllerImpl implements VideoDeletionController {

  private final VideoDeletionService videoDeletionService;

  public VideoDeletionControllerImpl(VideoDeletionService videoDeletionService) {
    this.videoDeletionService = videoDeletionService;
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteVideo(@PathVariable UUID id) {
    log.info("Requisição [DELETE] recebida em [/videos/{}]", id);
    videoDeletionService.deleteVideo(id);
    log.info("Requisição [DELETE] finalizada em [/videos/{}]", id);
    return ResponseEntity //
        .noContent() //
        .build();
  }
}