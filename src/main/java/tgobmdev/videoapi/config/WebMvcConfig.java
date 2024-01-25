package tgobmdev.videoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tgobmdev.videoapi.interceptor.AcceptLanguageInterceptor;
import tgobmdev.videoapi.resolver.AcceptHeaderResolver;
import tgobmdev.videoapi.util.ConstantsUtil;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Bean
  public AcceptLanguageInterceptor configureAcceptLanguageInterceptor() {
    return new AcceptLanguageInterceptor();
  }

  @Bean
  public LocaleResolver configureLocaleResolver() {
    AcceptHeaderResolver acceptHeaderResolver = new AcceptHeaderResolver();
    acceptHeaderResolver.setDefaultLocale(ConstantsUtil.BRAZILIAN_PORTUGUESE);
    return acceptHeaderResolver;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(configureAcceptLanguageInterceptor());
  }
}