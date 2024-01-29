package tgobmdev.videoapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import org.hibernate.validator.constraints.URL;

public record VideoRequest
    (@NotBlank String title, @NotBlank String description, @URL String url,
     Set<Long> idsCategoria) {

}