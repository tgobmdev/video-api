package tgobmdev.videoapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record VideoRequest
    (@NotBlank String title, @NotBlank String description, @URL String url) {

}