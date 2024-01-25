package tgobmdev.videoapi.exception;

import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.message.MessageManager;

@Getter
public class ApiException extends RuntimeException {

  private final int status;
  private final int codeMessage;
  private final String message;

  public ApiException(int status, MessageErrorEnum messageErrorEnum) {
    this.status = status;
    this.codeMessage = messageErrorEnum.getCode();
    this.message = MessageManager.getMessage(messageErrorEnum.getMessage(),
        LocaleContextHolder.getLocale());
  }

  public ApiException(int status, MessageErrorEnum messageErrorEnum, String... args) {
    this.status = status;
    this.codeMessage = messageErrorEnum.getCode();
    this.message = MessageManager.getMessage(messageErrorEnum.getMessage(),
        LocaleContextHolder.getLocale(), args);
  }

  public static ApiException of(int status, MessageErrorEnum messageErrorEnum) {
    return new ApiException(status, messageErrorEnum);
  }

  public static ApiException of(int status, MessageErrorEnum messageErrorEnum, String... args) {
    return new ApiException(status, messageErrorEnum, args);
  }
}