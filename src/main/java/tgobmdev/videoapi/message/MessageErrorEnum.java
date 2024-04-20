package tgobmdev.videoapi.message;

import lombok.Getter;

@Getter
public enum MessageErrorEnum {

  CODE_1(1, "video.not.found"), //
  CODE_2(2, "category.not.found"), //
  CODE_3(3, "category.free");

  private final int code;
  private final String message;

  MessageErrorEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }
}