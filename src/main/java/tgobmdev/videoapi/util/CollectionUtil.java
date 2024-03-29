package tgobmdev.videoapi.util;

import java.util.Collection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionUtil {

  public static <T> boolean isNullOrEmpty(Collection<T> collection) {
    return collection == null || collection.isEmpty();
  }
}