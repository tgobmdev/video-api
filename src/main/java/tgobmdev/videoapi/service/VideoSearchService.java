package tgobmdev.videoapi.service;

import java.util.List;
import tgobmdev.videoapi.dto.response.VideoResponse;

public interface VideoSearchService {

  List<VideoResponse> findAllActiveVideosByTitle(String title);
}