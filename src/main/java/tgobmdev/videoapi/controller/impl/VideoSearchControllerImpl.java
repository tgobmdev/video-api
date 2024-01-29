package tgobmdev.videoapi.controller.impl;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.VideoSearchController;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.service.VideoSearchService;

@Log4j2
@RestController
@RequestMapping(value = "videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoSearchControllerImpl implements VideoSearchController {

  private final VideoSearchService videoSearchService;

  public VideoSearchControllerImpl(VideoSearchService videoSearchService) {
    this.videoSearchService = videoSearchService;
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
}