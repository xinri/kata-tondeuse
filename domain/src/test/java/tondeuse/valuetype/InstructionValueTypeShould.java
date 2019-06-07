package tondeuse.valuetype;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class InstructionValueTypeShould {

  @ParameterizedTest(name = "throw an exception if the instruction is {0}")
  @MethodSource("elementToExclude")
  public void should_throw_exception(String value) {
    assertThrows(IllegalArgumentException.class, () -> new InstructionValueType(value));
  }

  static Stream<String> elementToExclude() {
    return "zertyuiopqsfhjklmwxcvbn1234567890".chars()
        .mapToObj(Character::toString)
        .map(String::toUpperCase);
  }

  @ParameterizedTest(name = "build the value type when the order is {0}")
  @ValueSource(strings = {"A", "D", "G"})
  public void should_build_direction(String value) {
    // when
    var result = new InstructionValueType(value);

    // then
    Assertions.assertThat(result.getValue()).isEqualTo(value);
  }
}