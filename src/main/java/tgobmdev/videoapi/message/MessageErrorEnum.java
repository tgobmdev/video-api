package tgobmdev.videoapi.message;

import lombok.Getter;

@Getter
public enum MessageErrorEnum {

  CODIGO_1(1, "video.nao.encontrado"),
  CODIGO_2(1, "categoria.nao.encontrado"),
  CODIGO_3(1, "categoria.livre");

  private final int code;
  private final String message;

  MessageErrorEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }
}