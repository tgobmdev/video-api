package tgobmdev.videoapi;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideoApiApplicationTests {

  @Test
  void givenApplicationContext_whenMainMethodExecuted_thenNoExceptionShouldBeThrown() {
    assertDoesNotThrow(() -> VideoApiApplication.main(new String[0]));
  }
}