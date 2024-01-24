package tgobmdev.videoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tgobmdev.videoapi.interceptor.LocaleInterceptor;
import tgobmdev.videoapi.resolver.AcceptHeaderResolver;
import tgobmdev.videoapi.util.ConstantsUtil;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeInterceptor());
  }

  @Bean
  public LocaleInterceptor localeInterceptor() {
    return new LocaleInterceptor();
  }

  @Bean
  public LocaleResolver sessionLocaleResolver() {
    AcceptHeaderResolver acceptHeaderResolver = new AcceptHeaderResolver();
    acceptHeaderResolver.setDefaultLocale(ConstantsUtil.BRAZILIAN_PORTUGUESE);
    return acceptHeaderResolver;
  }
}