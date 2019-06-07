package tondeuse.valuetype;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PositionWithLimitsShould {

  @Test
  public void should_throw_an_exception_when_x_is_negative() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new PositionWithLimits(-1, 0, 2, 2));
  }

  @Test
  public void should_throw_an_exception_when_y_is_negative() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new PositionWithLimits(0, -1, 2, 2));
  }

  @Test
  public void should_throw_an_exception_when_x_is_outside_max_x() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new PositionWithLimits(2, 2, 1, 2));
  }

  @Test
  public void should_throw_an_exception_when_y_is_outside_max_y() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new PositionWithLimits(2, 2, 2, 1));
  }

  @Test
  public void should_create_the_value_type_when_creating_one() {
    // when
    var result = new PositionWithLimits(0, 0, 1, 1);

    // then
    Assertions.assertThat(result)
        .hasFieldOrPropertyWithValue("position.x", 0)
        .hasFieldOrPropertyWithValue("position.y", 0)
        .hasFieldOrPropertyWithValue("limitField.limitMaxX", 1)
        .hasFieldOrPropertyWithValue("limitField.limitMaxY", 1);
  }
}