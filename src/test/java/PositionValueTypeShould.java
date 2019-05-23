import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PositionValueTypeShould {

  @Test
  public void should_throw_an_exception_when_x_is_negative() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new PositionValueType(-1, 0, 2, 2));
  }

  @Test
  public void should_throw_an_exception_when_y_is_negative() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new PositionValueType(0, -1, 2, 2));
  }

  @Test
  public void should_throw_an_exception_when_x_is_outside_max_x() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new PositionValueType(2, 2, 1, 2));
  }

  @Test
  public void should_throw_an_exception_when_y_is_outside_max_y() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new PositionValueType(2, 2, 2, 1));
  }

  @Test
  public void should_create_the_value_type_when_creating_one() {
    // when
    var result = new PositionValueType(0, 0, 1, 1);

    // then
    assertThat(result)
        .hasFieldOrPropertyWithValue("x", 0)
        .hasFieldOrPropertyWithValue("y", 0)
        .hasFieldOrPropertyWithValue("maxX", 1)
        .hasFieldOrPropertyWithValue("maxY", 1);
  }
}