package tgobmdev.videoapi.controller.impl;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.VideoEditingController;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.VideoEditingService;

@Log4j2
@RestController
@RequestMapping(value = "videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoEditingControllerImpl implements VideoEditingController {

  private final VideoEditingService videoEditingService;

  public VideoEditingControllerImpl(VideoEditingService videoEditingService) {
    this.videoEditingService = videoEditingService;
  }

  @Override
  @PatchMapping(value = "/{id}")
  public ResponseEntity<VideoResponse> editVideo(@PathVariable UUID id,
      @Valid @RequestBody VideoRequest videoRequest) {
    log.info("Requisição [PATCH] recebida em [/videos/{}]", id);
    VideoResponse videoResponse = videoEditingService.editVideo(id, videoRequest);
    log.info("Requisição [PATCH] finalizada em [/videos/{}]", id);
    return ResponseEntity.ok(videoResponse);
  }
}