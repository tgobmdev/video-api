package tgobmdev.videoapi.dto.response;

import java.util.List;

public record CategoryVideoResponse(Long id, String title, String color,
                                    List<VideoResponse> videoResponses) {

}