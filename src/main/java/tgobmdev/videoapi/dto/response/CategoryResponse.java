package tgobmdev.videoapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

public record CategoryResponse(Long id, String title, String color,
                               @JsonInclude(JsonInclude.Include.NON_EMPTY) List<VideoResponse> videos) {

}