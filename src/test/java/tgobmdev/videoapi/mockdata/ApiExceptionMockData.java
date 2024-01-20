package tgobmdev.videoapi.mockdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tgobmdev.videoapi.error.exception.ApiException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiExceptionMockData {

  public static ApiException getApiExceptionNotFound() {
    return new ApiException(404, "Not Found");
  }
}