package tgobmdev.videoapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
    ErrorResponse errorResponse = ErrorResponse.builder() //
        .status(ex.getStatus()) //
        .codeMessage(ex.getCodeMessage()) //
        .message(ex.getMessage()) //
        .build();
    return ResponseEntity //
        .status(errorResponse.getStatus()) //
        .body(errorResponse);
  }
}