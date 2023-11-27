package tgobmdev.challengealuraflixapi.dto;

import java.util.UUID;

public record VideoResponse(UUID id, String title, String description, String url) {

}