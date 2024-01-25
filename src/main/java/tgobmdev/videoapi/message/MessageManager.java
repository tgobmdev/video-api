package tgobmdev.videoapi.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageManager {

  private static final String BASE_NAME = "i18n/messages";

  public static String getMessage(String key, Locale locale) {
    ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale);
    return bundle.getString(key);
  }

  public static String getMessage(String key, Locale locale, Object... args) {
    ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale);
    String message = bundle.getString(key);
    if (Objects.nonNull(args) && args.length > 0) {
      message = MessageFormat.format(message, args);
    }
    return message;
  }
}