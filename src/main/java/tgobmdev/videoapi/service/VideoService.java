package tgobmdev.videoapi.service;

import java.util.List;
import java.util.UUID;
import tgobmdev.videoapi.dto.request.VideoFilter;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;

public interface VideoService {

  List<VideoResponse> findAllActiveVideos(VideoFilter videoFilter);

  VideoResponse findActiveVideoById(UUID videoId);

  VideoResponse createVideo(VideoRequest videoRequest);

  VideoResponse editVideo(UUID videoId, VideoRequest videoRequest);

  void deleteVideo(UUID videoId);
}