package tgobmdev.videoapi.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

  @Mock
  private ApiException apiException;

  @InjectMocks
  private RestExceptionHandler restExceptionHandler;

  @Test
  void handleApiException_thenReturnsErrorResponse() {
    when(apiException.getStatus()) //
        .thenReturn(HttpStatus.BAD_REQUEST.value());
    when(apiException.getCodeMessage()) //
        .thenReturn(0);
    when(apiException.getMessage()) //
        .thenReturn("Error message");

    ResponseEntity<ErrorResponse> responseEntity = restExceptionHandler.handleApiException(
        apiException);

    ErrorResponse errorResponse = responseEntity.getBody();
    assertNotNull(errorResponse);
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(0, errorResponse.getCodeMessage());
  }
}