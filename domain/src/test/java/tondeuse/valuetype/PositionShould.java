package tondeuse.valuetype;

import static org.junit.jupiter.api.Assertions.assertThrows;

import field.valuetype.LimitField;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PositionShould {

  @Test
  public void should_throw_an_exception_when_x_is_negative() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new Position(-1, 0));
  }

  @Test
  public void should_throw_an_exception_when_y_is_negative() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new Position(0, -1));
  }

  @Test
  public void should_throw_an_exception_when_both_are_negatives() {
    // when and then
    assertThrows(IllegalArgumentException.class,
        () -> new Position(-1, -1));
  }

  @Test
  public void return_a_position_with_y_increase_when_move_to_north() {
    // given
    var position = new Position(0, 0);

    // when
    var result = position.moveToNorth();

    // then
    Assertions.assertThat(result).isEqualTo(new Position(0, 1));
  }

  @Test
  public void return_a_position_with_y_increase_when_move_to_east() {
    // given
    var position = new Position(0, 0);

    // when
    var result = position.moveToEast();

    // then
    Assertions.assertThat(result).isEqualTo(new Position(1, 0));
  }

  @Test
  public void return_a_position_with_y_increase_when_move_to_West() {
    // given
    var position = new Position(5, 5);

    // when
    var result = position.moveToWest();

    // then
    Assertions.assertThat(result).isEqualTo(new Position(4, 5));
  }

  @Test
  public void return_a_position_with_y_increase_when_move_to_South() {
    // given
    var position = new Position(5, 5);

    // when
    var result = position.moveToSouth();

    // then
    Assertions.assertThat(result).isEqualTo(new Position(5, 4));
  }

  @Test
  public void return_the_same_position_when_move_to_north_and_is_at_the_top() {
    // given
    var position = new Position(2, 5);
    var limitField = new LimitField(5, 5);

    // when
    var result = position.moveToNorth(limitField);

    // then
    Assertions.assertThat(result).isEqualTo(new Position(2, 5));
  }

  @Test
  public void return_the_same_position_when_move_to_west_and_is_at_the_most_left() {
    // given
    var position = new Position(0, 2);
    var limitField = new LimitField(5, 5);

    // when
    var result = position.moveToWest(limitField);

    // then
    Assertions.assertThat(result).isEqualTo(new Position(0, 2));
  }

  @Test
  public void return_the_same_position_when_move_to_south_and_is_at_the_bottom() {
    // given
    var position = new Position(2, 0);
    var limitField = new LimitField(5, 5);

    // when
    var result = position.moveToSouth(limitField);

    // then
    Assertions.assertThat(result).isEqualTo(new Position(2, 0));
  }

  @Test
  public void return_the_same_position_when_move_to_east_and_is_at_the_most_right() {
    // given
    var position = new Position(5, 2);
    var limitField = new LimitField(5, 5);

    // when
    var result = position.moveToEast(limitField);

    // then
    Assertions.assertThat(result).isEqualTo(new Position(5, 2));
  }

}