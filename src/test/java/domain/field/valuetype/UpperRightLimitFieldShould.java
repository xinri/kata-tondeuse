package domain.field.valuetype;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UpperRightLimitFieldShould {

  @Test
  public void should_throw_an_exception_when_x_is_negative() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new UpperRightLimitField(-1, 0));
  }

  @Test
  public void should_throw_an_exception_when_y_is_negative() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new UpperRightLimitField(0, -1));
  }

  @Test
  public void should_build_limit_value_type_when_the_position_are_positive() {
    // when
    var result = new UpperRightLimitField(0, 0);
  }
}
