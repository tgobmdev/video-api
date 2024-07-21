package tgobmdev.videoapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonInclude(Include.NON_EMPTY)
public record CategoryResponse(Long id, String title, String color, List<VideoResponse> videos) {

}