package tgobmdev.videoapi.controller.impl;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.VideoController;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.VideoCreationService;
import tgobmdev.videoapi.service.VideoDeletionService;
import tgobmdev.videoapi.service.VideoDetailService;
import tgobmdev.videoapi.service.VideoEditingService;
import tgobmdev.videoapi.service.VideoListService;
import tgobmdev.videoapi.service.VideoSearchService;
import tgobmdev.videoapi.util.UriUtil;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoControllerImpl implements VideoController {

  private final VideoListService videoListService;
  private final VideoDetailService videoDetailService;
  private final VideoSearchService videoSearchService;
  private final VideoCreationService videoCreationService;
  private final VideoEditingService videoEditingService;
  private final VideoDeletionService videoDeletionService;

  @Override
  @GetMapping(value = "/list")
  public ResponseEntity<List<VideoResponse>> findAllActiveVideos() {
    log.info("Requisição [GET] recebida em [/videos]");
    List<VideoResponse> videoResponses = videoListService.findAllActiveVideos();
    log.info("Requisição [GET] finalizada em [/videos]");
    return ResponseEntity.ok(videoResponses);
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

  @Override
  @GetMapping
  public ResponseEntity<List<VideoResponse>> findAllActiveVideosByTitle(
      @RequestParam String search) {
    log.info("Requisição [GET] recebida em [/videos?search={}]", search);
    List<VideoResponse> videoResponsesByTitle = videoSearchService.findAllActiveVideosByTitle(
        search);
    log.info("Requisição [GET] finalizada em [/videos?search={}]", search);
    return ResponseEntity.ok(videoResponsesByTitle);
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

  @Override
  @PatchMapping(value = "/{id}")
  public ResponseEntity<VideoResponse> editVideo(@PathVariable UUID id,
      @Valid @RequestBody VideoRequest videoRequest) {
    log.info("Requisição [PATCH] recebida em [/videos/{}]", id);
    VideoResponse videoResponse = videoEditingService.editVideo(id, videoRequest);
    log.info("Requisição [PATCH] finalizada em [/videos/{}]", id);
    return ResponseEntity.ok(videoResponse);
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