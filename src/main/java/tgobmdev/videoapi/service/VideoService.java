package tgobmdev.videoapi.service;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;

public interface VideoService {

  List<VideoResponse> findAllActiveVideos();

  VideoResponse findActiveVideoById(UUID id, HttpHeaders httpHeaders);

  List<VideoResponse> findAllActiveVideosByTitle(String title);

  VideoResponse createVideo(VideoRequest videoRequest);

  VideoResponse editVideo(UUID id, VideoRequest videoRequest);

  void deleteVideo(UUID id);

  List<VideoResponse> findByCategoriaEntitiesId(Long idCategoria);
}