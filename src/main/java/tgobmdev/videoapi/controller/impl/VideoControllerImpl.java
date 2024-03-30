package tgobmdev.videoapi.controller.impl;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tgobmdev.videoapi.controller.VideoController;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.VideoService;

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
    log.info("Request [GET] received at [/videos]");
    List<VideoResponse> activeVideos = videoService.findAllActiveVideos();
    log.info("Request [GET] finished at [/videos]");
    return ResponseEntity.ok(activeVideos);
  }

  @Override
  @GetMapping(value = "/{videoId}")
  public ResponseEntity<VideoResponse> findActiveVideoById(@PathVariable UUID videoId) {
    log.info("Request [GET] received at [/videos/{}]", videoId);
    VideoResponse activeVideo = videoService.findActiveVideoById(videoId);
    log.info("Request [GET] finished at [/videos/{}]", videoId);
    return ResponseEntity.ok(activeVideo);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<VideoResponse>> findAllActiveVideosByTitle(
      @RequestParam String search) {
    log.info("Request [GET] received at [/videos?search={}]", search);
    List<VideoResponse> activeVideosByTitle = videoService.findAllActiveVideosByTitle(search);
    log.info("Request [GET] finished at [/videos?search={}]", search);
    return ResponseEntity.ok(activeVideosByTitle);
  }

  @Override
  @PostMapping
  public ResponseEntity<VideoResponse> createVideo(@Valid @RequestBody VideoRequest videoRequest) {
    log.info("Request [POST] received at [/videos]");
    VideoResponse createdVideo = videoService.createVideo(videoRequest);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
        .path("/{videoId}") //
        .buildAndExpand(createdVideo.id()) //
        .toUri();
    log.info("Request [POST] finished at [/videos]");
    return ResponseEntity //
        .created(location) //
        .build();
  }

  @Override
  @PatchMapping(value = "/{videoId}")
  public ResponseEntity<VideoResponse> editVideo(@PathVariable UUID videoId,
      @Valid @RequestBody VideoRequest videoRequest) {
    log.info("Request [PATCH] received at [/videos/{}]", videoId);
    VideoResponse editedVideo = videoService.editVideo(videoId, videoRequest);
    log.info("Request [PATCH] finished at [/videos/{}]", videoId);
    return ResponseEntity.ok(editedVideo);
  }

  @Override
  @DeleteMapping("/{videoId}")
  public ResponseEntity<Void> deleteVideo(@PathVariable UUID videoId) {
    log.info("Request [DELETE] received at [/videos/{}]", videoId);
    videoService.deleteVideo(videoId);
    log.info("Request [DELETE] finished at [/videos/{}]", videoId);
    return ResponseEntity //
        .noContent() //
        .build();
  }
}