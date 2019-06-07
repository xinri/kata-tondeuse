package field.valuetype;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LimitFieldShould {

  @Test
  public void should_throw_an_exception_when_x_is_negative() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new LimitField(-1, 0));
  }

  @Test
  public void should_throw_an_exception_when_y_is_negative() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new LimitField(0, -1));
  }

  @Test
  public void should_build_limit_value_type_when_the_position_are_positive() {
    // when
    var result = new LimitField(0, 0);
  }

  @ParameterizedTest(name = "return true when ({0}, {1}) is not in the field which the upper right is (5, 5)")
  @CsvSource({"-1, -1", "-1, 0", "0, -1", "6, 5", "5, 6", "6, 6"})
  public void return_true_when_is_not_in_the_field(int x, int y) {
    // given
    var limitField = new LimitField(5, 5);

    // when
    var result = limitField.isOutOfBounds(x, y);

    // then
    Assertions.assertThat(result).isTrue();
  }

  @ParameterizedTest(name = "return false when ({0}, {1}) is in the field which the upper right is (5, 5)")
  @CsvSource({"0, 0", "5, 5", "2, 2"})
  public void return_false_when_is_in_the_field(int x, int y) {
    // given
    var limitField = new LimitField(5, 5);

    // when
    var result = limitField.isOutOfBounds(x, y);

    // then
    Assertions.assertThat(result).isFalse();
  }
}
