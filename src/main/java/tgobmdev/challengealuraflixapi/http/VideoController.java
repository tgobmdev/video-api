package tgobmdev.challengealuraflixapi.http;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.challengealuraflixapi.dto.VideoCreateRequest;
import tgobmdev.challengealuraflixapi.dto.VideoResponse;
import tgobmdev.challengealuraflixapi.service.VideoService;

@RestController
@RequestMapping(value = "videos")
public class VideoController {

  private final VideoService videoService;

  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  @GetMapping
  public ResponseEntity<List<VideoResponse>> findAllActiveVideos() {
    return ResponseEntity.ok(videoService.findAllActiveVideos());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<VideoResponse> findActiveVideoById(@PathVariable UUID id) {
    return ResponseEntity.ok(videoService.findActiveVideoById(id));
  }

  @PostMapping
  public ResponseEntity<VideoResponse> createVideo(
      @Valid @RequestBody VideoCreateRequest videoCreateRequest) {
    return ResponseEntity.ok(videoService.createVideo(videoCreateRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteVideo(@PathVariable UUID id) {
    videoService.deleteVideo(id);
    return ResponseEntity.noContent().build();
  }
}