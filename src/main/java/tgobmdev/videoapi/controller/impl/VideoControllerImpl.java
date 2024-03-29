package tgobmdev.videoapi.controller.impl;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
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
import tgobmdev.videoapi.service.VideoService;
import tgobmdev.videoapi.util.UriUtil;

@Log4j2
@RestController
@RequestMapping(value = "videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoControllerImpl implements VideoController {

  private final VideoService videoService;

  public VideoControllerImpl(VideoService videoService) {
    this.videoService = videoService;
  }

  @Override
  @GetMapping(value = "/list")
  public ResponseEntity<List<VideoResponse>> findAllActiveVideos() {
    log.info("Request [GET] received em [/videos]");
    List<VideoResponse> videoResponses = videoService.findAllActiveVideos();
    log.info("Request [GET] finished em [/videos]");
    return ResponseEntity.ok(videoResponses);
  }

  @Override
  @GetMapping(value = "/{videoId}")
  public ResponseEntity<VideoResponse> findActiveVideoById(@PathVariable UUID videoId,
      @RequestHeader HttpHeaders httpHeaders) {
    log.info("Request [GET] received em [/videos/{}]", videoId);
    VideoResponse videoResponse = videoService.findActiveVideoById(videoId, httpHeaders);
    log.info("Request [GET] finished em [/videos/{}]", videoId);
    return ResponseEntity.ok(videoResponse);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<VideoResponse>> findAllActiveVideosByTitle(
      @RequestParam String search) {
    log.info("Request [GET] received em [/videos?search={}]", search);
    List<VideoResponse> videoResponsesByTitle = videoService.findAllActiveVideosByTitle(search);
    log.info("Request [GET] finished em [/videos?search={}]", search);
    return ResponseEntity.ok(videoResponsesByTitle);
  }

  @Override
  @PostMapping
  public ResponseEntity<VideoResponse> createVideo(@Valid @RequestBody VideoRequest videoRequest) {
    log.info("Request [POST] received em [/videos]");
    VideoResponse videoResponse = videoService.createVideo(videoRequest);
    URI location = UriUtil.buildUriFindById(videoResponse.id());
    log.info("Request [POST] finished em [/videos]");
    return ResponseEntity //
        .created(location) //
        .build();
  }

  @Override
  @PatchMapping(value = "/{videoId}")
  public ResponseEntity<VideoResponse> editVideo(@PathVariable UUID videoId,
      @Valid @RequestBody VideoRequest videoRequest) {
    log.info("Request [PATCH] received em [/videos/{}]", videoId);
    VideoResponse videoResponse = videoService.editVideo(videoId, videoRequest);
    log.info("Request [PATCH] finished em [/videos/{}]", videoId);
    return ResponseEntity.ok(videoResponse);
  }

  @Override
  @DeleteMapping("/{videoId}")
  public ResponseEntity<Void> deleteVideo(@PathVariable UUID videoId) {
    log.info("Request [DELETE] received em [/videos/{}]", videoId);
    videoService.deleteVideo(videoId);
    log.info("Request [DELETE] finished em [/videos/{}]", videoId);
    return ResponseEntity //
        .noContent() //
        .build();
  }
}