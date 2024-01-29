package tgobmdev.videoapi.controller.impl;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.VideoListController;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.VideoListService;

@Log4j2
@RestController
@RequestMapping(value = "videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoListControllerImpl implements VideoListController {

  private final VideoListService videoListService;

  public VideoListControllerImpl(VideoListService videoListService) {
    this.videoListService = videoListService;
  }

  @Override
  @GetMapping
  public ResponseEntity<List<VideoResponse>> findAllActiveVideos() {
    log.info("Requisição [GET] recebida em [/videos]");
    List<VideoResponse> videoResponses = videoListService.findAllActiveVideos();
    log.info("Requisição [GET] finalizada em [/videos]");
    return ResponseEntity.ok(videoResponses);
  }
}