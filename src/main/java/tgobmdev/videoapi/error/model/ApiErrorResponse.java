package tgobmdev.videoapi.error.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public final class ApiErrorResponse {

  private final int status;
  private final int codeMessage;
  private final String message;
  @Builder.Default
  private final LocalDateTime time = LocalDateTime.now();
}