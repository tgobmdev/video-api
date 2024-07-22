package tgobmdev.videoapi.annotation.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
class LoggingImplTest {

  @InjectMocks
  private LoggingImpl loggingImpl;

  @Mock
  private HttpServletRequest request;

  @Mock
  private JoinPoint joinPoint;

  @BeforeEach
  public void setUp() {
    ServletRequestAttributes attributes = new ServletRequestAttributes(request);
    RequestContextHolder.setRequestAttributes(attributes);
  }

  @Test
  void givenGetRequest_whenLogBefore_thenVerifyRequestDetails() {
    when(request.getMethod()).thenReturn("GET");
    when(request.getRequestURI()).thenReturn("/test-uri");
    when(joinPoint.getArgs()).thenReturn(new Object[]{"param1", "param2"});

    loggingImpl.logBefore(joinPoint);

    verify(request, times(1)).getMethod();
    verify(request, times(1)).getRequestURI();
  }

  @Test
  void givenPostRequest_whenLogAfterReturning_thenVerifyRequestDetails() {
    when(request.getMethod()).thenReturn("POST");
    when(request.getRequestURI()).thenReturn("/test-uri");
    Object result = "result";

    loggingImpl.logAfterReturning(result);

    verify(request, times(1)).getMethod();
    verify(request, times(1)).getRequestURI();
  }

  @Test
  void givenDeleteRequest_whenLogAfterThrowing_thenVerifyRequestDetails() {
    when(request.getMethod()).thenReturn("DELETE");
    when(request.getRequestURI()).thenReturn("/test-uri");
    Throwable exception = new RuntimeException("Error message");

    loggingImpl.logAfterThrowing(exception);

    verify(request, times(1)).getMethod();
    verify(request, times(1)).getRequestURI();
  }
}