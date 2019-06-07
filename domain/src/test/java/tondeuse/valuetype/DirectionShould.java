package tondeuse.valuetype;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class DirectionShould {

  @ParameterizedTest(name = "throw an exception if the direction is {0}")
  @MethodSource("elementToExclude")
  public void should_throw_exception(String value) {
    assertThrows(IllegalArgumentException.class, () -> new Direction(value));
  }

  static Stream<String> elementToExclude() {
    return "azrtyuiopqdfghjklmxcvb1234567890".chars()
        .mapToObj(Character::toString)
        .map(String::toUpperCase);
  }

  @ParameterizedTest(name = "build the value type when the direction is {0}")
  @ValueSource(strings = {"N", "W", "S", "E"})
  public void should_build_direction(String value) {
    // when
    var result = new Direction(value);

    // then
    Assertions.assertThat(result.getDirection()).isEqualTo(value);
  }
}