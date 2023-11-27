package tgobmdev.challengealuraflixapi.error;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tgobmdev.challengealuraflixapi.error.exception.ApiException;
import tgobmdev.challengealuraflixapi.error.model.ApiErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiErrorResponse> handleApiException(ApiException e) {
    ApiErrorResponse apiErrorResponse = e.getApiErrorResponse();
    return new ResponseEntity<>(apiErrorResponse, HttpStatusCode.valueOf(apiErrorResponse.code()));
  }
}