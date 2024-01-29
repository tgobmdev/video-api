package tgobmdev.videoapi.service;

import java.util.UUID;
import org.springframework.http.HttpHeaders;
import tgobmdev.videoapi.dto.response.VideoResponse;

public interface VideoDetailService {

  VideoResponse findActiveVideoById(UUID id, HttpHeaders httpHeaders);
}