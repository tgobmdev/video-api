package tgobmdev.videoapi.controller.impl;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.VideoCreationController;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.VideoCreationService;
import tgobmdev.videoapi.util.UriUtil;

@Log4j2
@RestController
@RequestMapping(value = "videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoCreationControllerImpl implements VideoCreationController {

  private final VideoCreationService videoCreationService;

  public VideoCreationControllerImpl(VideoCreationService videoCreationService) {
    this.videoCreationService = videoCreationService;
  }

  @Override
  @PostMapping
  public ResponseEntity<VideoResponse> createVideo(@Valid @RequestBody VideoRequest videoRequest) {
    log.info("Requisição [POST] recebida em [/videos]");
    VideoResponse videoResponse = videoCreationService.createVideo(videoRequest);
    URI location = UriUtil.buildUriFindById(videoResponse.id());
    log.info("Requisição [POST] finalizada em [/videos]");
    return ResponseEntity //
        .created(location) //
        .build();
  }
}