package tgobmdev.videoapi.service;

import java.util.UUID;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;

public interface VideoEditingService {

  VideoResponse editVideo(UUID id, VideoRequest videoRequest);
}