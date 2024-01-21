package tgobmdev.videoapi.util;

import java.net.URI;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UriUtil {

  public static URI buildUriFindById(UUID id) {
    return ServletUriComponentsBuilder.fromCurrentRequest() //
        .path("/{id}") //
        .buildAndExpand(id) //
        .toUri();
  }
}