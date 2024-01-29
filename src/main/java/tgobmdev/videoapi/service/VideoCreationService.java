package tgobmdev.videoapi.service;

import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;

public interface VideoCreationService {

  VideoResponse createVideo(VideoRequest videoRequest);
}