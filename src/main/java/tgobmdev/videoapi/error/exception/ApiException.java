package tgobmdev.videoapi.error.exception;

import java.io.Serial;
import lombok.Getter;
import tgobmdev.videoapi.error.model.ApiErrorResponse;

@Getter
public class ApiException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -4283381744857865643L;

  private final ApiErrorResponse apiErrorResponse;

  public ApiException(int code, String message) {
    this.apiErrorResponse = new ApiErrorResponse(code, message);
  }

  public ApiException(int code, String field, String message) {
    this.apiErrorResponse = new ApiErrorResponse(code, field, message);
  }
}