package tgobmdev.challengealuraflixapi.error.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serial;
import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
public record ApiErrorResponse(int code, String field, String message) implements Serializable {

  @Serial
  private static final long serialVersionUID = -2123971128507189391L;

  public ApiErrorResponse(int code, String message) {
    this(code, null, message);
  }
}