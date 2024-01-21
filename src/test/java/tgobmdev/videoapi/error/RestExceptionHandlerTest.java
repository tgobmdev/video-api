package tgobmdev.videoapi.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tgobmdev.videoapi.error.exception.ApiException;
import tgobmdev.videoapi.error.handle.RestExceptionHandler;
import tgobmdev.videoapi.error.model.ApiErrorResponse;
import tgobmdev.videoapi.mockdata.ApiExceptionMockData;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

  @InjectMocks
  private RestExceptionHandler restExceptionHandler;

  @Test
  void givenApiExceptionNotFound_whenHandleApiException_thenReturnNotFoundResponse() {
    ApiException mockApiException = ApiExceptionMockData.getApiExceptionNotFound();

    ResponseEntity<ApiErrorResponse> result = restExceptionHandler //
        .handleApiException(mockApiException);

    assertNotNull(result.getBody());
    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    assertEquals(mockApiException.getStatus(), result.getBody().getStatus());
  }
}