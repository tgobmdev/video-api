package tgobmdev.videoapi.resolver;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import tgobmdev.videoapi.util.ConstantsUtil;

public class AcceptHeaderResolver extends AcceptHeaderLocaleResolver {

  private static final List<Locale> LOCALES = List.of(Locale.ENGLISH,
      ConstantsUtil.BRAZILIAN_PORTUGUESE);

  @Override
  @NonNull
  public Locale resolveLocale(HttpServletRequest request) {
    String language = request.getHeader("Accept-Language");
    return StringUtils.isEmpty(language) ? Locale.getDefault()
        : Locale.lookup(Locale.LanguageRange.parse(language), LOCALES);
  }
}