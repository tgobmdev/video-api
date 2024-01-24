package tgobmdev.videoapi.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tgobmdev.videoapi.exception.ApiException;
import tgobmdev.videoapi.message.MessageErrorEnum;
import tgobmdev.videoapi.util.ConstantsUtil;

public class LocaleInterceptor implements HandlerInterceptor {

  private static final List<Locale> ALLOWED_LOCALES = List.of(Locale.ENGLISH,
      ConstantsUtil.BRAZILIAN_PORTUGUESE);

  @Override
  public boolean preHandle(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull Object handler) {
    String language = request.getHeader("Accept-Language");
    Locale locale = Objects.nonNull(language) ? Locale.forLanguageTag(language)
        : ConstantsUtil.BRAZILIAN_PORTUGUESE;
    if (!ALLOWED_LOCALES.contains(locale)) {
      throw ApiException.of(400, MessageErrorEnum.CODIGO_1);
    }
    LocaleContextHolder.setLocale(locale);
    return true;
  }

  @Override
  public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull Object handler, ModelAndView modelAndView) {
    LocaleContextHolder.resetLocaleContext();
  }
}