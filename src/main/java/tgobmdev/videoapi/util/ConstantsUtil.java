package tgobmdev.videoapi.util;

import java.util.Locale;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantsUtil {

  public static final Locale BRAZILIAN_PORTUGUESE = Locale.of("pt", "BR");
  public static final String ACCEPT_LANGUAGE = "Accept-Language";
}