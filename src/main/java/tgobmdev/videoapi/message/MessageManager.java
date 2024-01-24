package tgobmdev.videoapi.message;

import java.util.Locale;
import java.util.ResourceBundle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageManager {

  private static final String BASE_NAME = "i18n/messages";
  private static final ResourceBundle DEFAULT_BUNDLE = ResourceBundle.getBundle(BASE_NAME,
      Locale.getDefault());

  public static String getMessage(String key) {
    return DEFAULT_BUNDLE.getString(key);
  }

  public static String getMessage(String key, Locale locale) {
    ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale);
    return bundle.getString(key);
  }
}