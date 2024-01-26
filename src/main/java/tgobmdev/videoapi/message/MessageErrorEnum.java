package tgobmdev.videoapi.message;

import lombok.Getter;

@Getter
public enum MessageErrorEnum {
  CODIGO_1(1, "video.nao.encontrado");

  private final int code;
  private final String message;

  MessageErrorEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }
}