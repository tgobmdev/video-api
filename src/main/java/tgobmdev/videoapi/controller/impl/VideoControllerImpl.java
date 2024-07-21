package tgobmdev.videoapi.controller.impl;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
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
import tgobmdev.videoapi.annotation.Logging;
import tgobmdev.videoapi.controller.VideoController;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.VideoService;

@RestController
@RequestMapping(value = "videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoControllerImpl implements VideoController {

  private final VideoService videoService;

  public VideoControllerImpl(VideoService videoService) {
    this.videoService = videoService;
  }

  @Logging
  @Override
  @GetMapping(value = "/list")
  public ResponseEntity<List<VideoResponse>> findAllActiveVideos() {
    return ResponseEntity.ok(videoService.findAllActiveVideos());
  }

  @Logging
  @Override
  @GetMapping(value = "/{videoId}")
  public ResponseEntity<VideoResponse> findActiveVideoById(@PathVariable UUID videoId) {
    return ResponseEntity.ok(videoService.findActiveVideoById(videoId));
  }

  @Logging
  @Override
  @GetMapping
  public ResponseEntity<List<VideoResponse>> findAllActiveVideosByTitle(
      @RequestParam String search) {
    return ResponseEntity.ok(videoService.findAllActiveVideosByTitle(search));
  }

  @Logging
  @Override
  @PostMapping
  public ResponseEntity<VideoResponse> createVideo(@Valid @RequestBody VideoRequest videoRequest) {
    VideoResponse createdVideo = videoService.createVideo(videoRequest);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{videoId}")
        .buildAndExpand(createdVideo.id())
        .toUri();
    return ResponseEntity.created(location)
        .build();
  }

  @Logging
  @Override
  @PatchMapping(value = "/{videoId}")
  public ResponseEntity<VideoResponse> editVideo(@PathVariable UUID videoId,
      @Valid @RequestBody VideoRequest videoRequest) {
    return ResponseEntity.ok(videoService.editVideo(videoId, videoRequest));
  }

  @Logging
  @Override
  @DeleteMapping("/{videoId}")
  public ResponseEntity<Void> deleteVideo(@PathVariable UUID videoId) {
    videoService.deleteVideo(videoId);
    return ResponseEntity.noContent()
        .build();
  }
}