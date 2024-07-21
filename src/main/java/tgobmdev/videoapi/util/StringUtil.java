package tgobmdev.videoapi.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

  public static String truncate(String message, int maxLength) {
    if (message.length() > maxLength) {
      return message.substring(0, maxLength) + "...";
    }
    return message;
  }
}