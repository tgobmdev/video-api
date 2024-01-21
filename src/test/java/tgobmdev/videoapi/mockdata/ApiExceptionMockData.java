package tgobmdev.videoapi.mockdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tgobmdev.videoapi.error.enumeration.ErrorEnum;
import tgobmdev.videoapi.error.exception.ApiException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiExceptionMockData {

  public static ApiException getApiExceptionNotFound() {
    return ApiException.of(404, ErrorEnum.VIDEO_NOT_FOUND);
  }
}