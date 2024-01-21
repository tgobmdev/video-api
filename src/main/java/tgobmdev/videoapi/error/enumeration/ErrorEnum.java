package tgobmdev.videoapi.error.enumeration;

import lombok.Getter;

@Getter
public enum ErrorEnum {
  VIDEO_NOT_FOUND(1, "Video Not Found");

  private final int code;
  private final String message;

  ErrorEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }
}