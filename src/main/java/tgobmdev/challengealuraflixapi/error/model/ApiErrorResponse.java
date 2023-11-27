package tgobmdev.challengealuraflixapi.error.model;

import java.io.Serial;
import java.io.Serializable;

public record ApiErrorResponse(int code, String message) implements Serializable {

  @Serial
  private static final long serialVersionUID = -2123971128507189391L;
}