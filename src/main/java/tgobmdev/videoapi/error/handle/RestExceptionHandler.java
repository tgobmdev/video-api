package tgobmdev.videoapi.error.handle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tgobmdev.videoapi.error.exception.ApiException;
import tgobmdev.videoapi.error.model.ApiErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
    ApiErrorResponse errorResponse = ApiErrorResponse.builder() //
        .status(ex.getStatus()) //
        .codeMessage(ex.getErrorEnum().getCode()) //
        .message(ex.getErrorEnum().getMessage()) //
        .build();
    return ResponseEntity //
        .status(errorResponse.getStatus()) //
        .body(errorResponse);
  }
}