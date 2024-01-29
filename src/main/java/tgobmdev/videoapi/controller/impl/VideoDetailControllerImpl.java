package tgobmdev.videoapi.controller.impl;

import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.VideoDetailController;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.VideoDetailService;

@Log4j2
@RestController
@RequestMapping(value = "videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoDetailControllerImpl implements VideoDetailController {

  private final VideoDetailService videoDetailService;

  public VideoDetailControllerImpl(VideoDetailService videoDetailService) {
    this.videoDetailService = videoDetailService;
  }

  @Override
  @GetMapping(value = "/{id}")
  public ResponseEntity<VideoResponse> findActiveVideoById(@PathVariable UUID id,
      @RequestHeader HttpHeaders httpHeaders) {
    log.info("Requisição [GET] recebida em [/videos/{}]", id);
    VideoResponse videoResponse = videoDetailService.findActiveVideoById(id, httpHeaders);
    log.info("Requisição [GET] finalizada em [/videos/{}]", id);
    return ResponseEntity.ok(videoResponse);
  }
}