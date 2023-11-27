package tgobmdev.challengealuraflixapi.mockdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tgobmdev.challengealuraflixapi.error.exception.ApiException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiExceptionMockData {

  public static ApiException getApiExceptionNotFound() {
    return new ApiException(404, "Not Found");
  }
}