package tgobmdev.videoapi.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(@NotBlank String title, @NotBlank String color) {

}