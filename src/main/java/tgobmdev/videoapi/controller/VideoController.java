package tgobmdev.videoapi.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.VideoService;
import tgobmdev.videoapi.util.UriUtil;

@Log4j2
@RestController
@RequestMapping(value = "videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoController {

  private final VideoService videoService;

  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  @GetMapping
  public ResponseEntity<List<VideoResponse>> findAllActiveVideos() {
    log.info("Requisição [GET] recebida em [/videos]");
    List<VideoResponse> videoResponses = videoService.findAllActiveVideos();
    log.info("Requisição [GET] finalizada em [/videos]");
    return ResponseEntity.ok(videoResponses);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<VideoResponse> findActiveVideoById(@PathVariable UUID id) {
    log.info("Requisição [GET] recebida em [/videos/{}]", id);
    VideoResponse videoResponse = videoService.findActiveVideoById(id);
    log.info("Requisição [GET] finalizada em [/videos/{}]", id);
    return ResponseEntity.ok(videoResponse);
  }

  @PostMapping
  public ResponseEntity<VideoResponse> createVideo(@Valid @RequestBody VideoRequest videoRequest) {
    log.info("Requisição [POST] recebida em [/videos]");
    VideoResponse videoResponse = videoService.createVideo(videoRequest);
    URI location = UriUtil.buildUriFindById(videoResponse.id());
    log.info("Requisição [POST] finalizada em [/videos]");
    return ResponseEntity //
        .created(location) //
        .build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteVideo(@PathVariable UUID id) {
    log.info("Requisição [DELETE] recebida em [/videos/{}]", id);
    videoService.deleteVideo(id);
    log.info("Requisição [DELETE] finalizada em [/videos/{}]", id);
    return ResponseEntity //
        .noContent() //
        .build();
  }
}