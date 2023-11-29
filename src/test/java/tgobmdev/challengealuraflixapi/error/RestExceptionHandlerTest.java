package tgobmdev.challengealuraflixapi.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tgobmdev.challengealuraflixapi.error.model.ApiErrorResponse;
import tgobmdev.challengealuraflixapi.mockdata.ApiExceptionMockData;

@ExtendWith(MockitoExtension.class)
public class RestExceptionHandlerTest {

  @InjectMocks
  private RestExceptionHandler restExceptionHandler;

  @Test
  void testHandleApiException() {
    ResponseEntity<ApiErrorResponse> result = restExceptionHandler.handleApiException(
        ApiExceptionMockData.getApiExceptionNotFound());

    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(404, result.getBody().code());
  }
}