package tgobmdev.challengealuraflixapi.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VideoDeleteResponse(UUID id, String title, String description, String url,
                                  LocalDateTime deletedAt, Boolean deleted) {

}