package tgobmdev.videoapi.service.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import tgobmdev.videoapi.component.VideoComponent;
import tgobmdev.videoapi.service.VideoDeletionService;

@Service
public class VideoDeletionServiceImpl implements VideoDeletionService {

  private final VideoComponent videoComponent;

  public VideoDeletionServiceImpl(VideoComponent videoComponent) {
    this.videoComponent = videoComponent;
  }

  @Override
  public void deleteVideo(UUID id) {
    videoComponent.deleteVideo(id);
  }
}