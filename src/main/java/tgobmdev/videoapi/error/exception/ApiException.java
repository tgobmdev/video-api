package tgobmdev.videoapi.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tgobmdev.videoapi.error.enumeration.ErrorEnum;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class ApiException extends RuntimeException {

  private final int status;
  private final ErrorEnum errorEnum;
}