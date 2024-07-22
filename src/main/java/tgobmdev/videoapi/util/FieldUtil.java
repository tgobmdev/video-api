package tgobmdev.videoapi.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldUtil {

  public static <T> boolean acceptIfDifferent(Supplier<T> getter, Consumer<T> setter, T newValue) {
    T currentValue = getter.get();
    if (Objects.nonNull(newValue) && !Objects.equals(newValue, currentValue)) {
      setter.accept(newValue);
      return true;
    }
    return false;
  }
}