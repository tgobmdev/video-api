package tgobmdev.challengealuraflixapi.error;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tgobmdev.challengealuraflixapi.error.exception.ApiException;
import tgobmdev.challengealuraflixapi.error.model.ApiErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
    ApiErrorResponse apiErrorResponse = ex.getApiErrorResponse();
    return new ResponseEntity<>(apiErrorResponse, HttpStatusCode.valueOf(apiErrorResponse.code()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ApiErrorResponse>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    List<ApiErrorResponse> apiErrorResponses = ex.getAllErrors().stream().map(objectError -> {
      int code = ex.getBody().getStatus();
      String fieldName = ((FieldError) objectError).getField();
      String errorMessage = objectError.getDefaultMessage();
      String message = String.join(": ", fieldName, errorMessage);
      return new ApiException(code, fieldName, message).getApiErrorResponse();
    }).collect(Collectors.toList());
    return new ResponseEntity<>(apiErrorResponses, HttpStatus.BAD_REQUEST);
  }
}