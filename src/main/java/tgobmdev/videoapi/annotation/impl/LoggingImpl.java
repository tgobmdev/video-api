package tgobmdev.videoapi.annotation.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tgobmdev.videoapi.util.StringUtil;

@Aspect
@Log4j2
@Component
public class LoggingImpl {

  @Before("@annotation(tgobmdev.videoapi.annotation.Logging)")
  public void logBefore(JoinPoint joinPoint) {
    log.info("Initiating request [{}] to [{}] with parameters [{}]", getMethod(), getRequestURI(),
        joinPoint.getArgs());
  }

  @AfterReturning(pointcut = "@annotation(tgobmdev.videoapi.annotation.Logging)", returning = "result")
  public void logAfterReturning(Object result) {
    String message = StringUtil.truncate(result.toString(), 100);
    log.info("Request [{}] to [{}] completed successfully, result [{}]", getMethod(),
        getRequestURI(), message);
  }

  @AfterThrowing(pointcut = "@annotation(tgobmdev.videoapi.annotation.Logging)", throwing = "exception")
  public void logAfterThrowing(Throwable exception) {
    log.error("Error processing request [{}] for [{}]: {}", getMethod(), getRequestURI(),
        exception.getMessage(), exception);
  }

  private String getMethod() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    return request.getMethod();
  }

  private String getRequestURI() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    return request.getRequestURI();
  }
}