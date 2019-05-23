import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TondeuseShould {

  @Nested
  @DisplayName("change the direction to ")
  class ChangeDirectionShould {

    @ParameterizedTest(name = "{1} when the tondeuse is oriented to "
        + "{0} with the order G")
    @CsvSource({"N, W", "W, S", "S, E", "E, N"})
    public void changeDirectionWithOrderG(String originalDirection, String expectedDirection) {
      assert_direction_with_order_with_G(originalDirection, expectedDirection);
    }

    @ParameterizedTest(name = "{1} when the tondeuse is oriented to "
        + "{0} with the order D")
    @CsvSource({"N, E", "W, N", "S, W", "E, S"})
    public void changeDirectionWithOrderD(String originalDirection, String expectedDirection) {
      assert_direction_with_order_with_D(originalDirection, expectedDirection);
    }

    private void assert_direction_with_order_with_D(
        String originalDirection, String expectedDirection) {
      assert_direction_with_order_with(originalDirection, "D", expectedDirection);
    }

    private void assert_direction_with_order_with_G(
        String originalDirection, String expectedDirection) {
      assert_direction_with_order_with(originalDirection, "G", expectedDirection);
    }

    private void assert_direction_with_order_with(
        String originalDirection, String orderDirection, String expectedDirection) {
      // given
      var tondeuse = new Tondeuse(
          new PositionValueType(0, 0, 10, 10),
          new DirectionValueType(originalDirection));

      // when
      tondeuse.order(orderDirection);

      // then
      assertThat(tondeuse.getDirection()).isEqualTo(expectedDirection);
    }
  }

  @Nested
  @DisplayName("advance to")
  class AdvanceShould {

    @ParameterizedTest(name = "({0}, {1}) when the tondeuse is at (5, 5), {3} oriented")
    @CsvSource({"5, 6, N", "4, 5, W", "5, 4, S", "6, 5, E"})
    public void advanceTo(Integer expectedX, Integer expectedY, String orientation) {
      // given
      var tondeuse = new Tondeuse(
          new PositionValueType(5, 5, 10, 10),
          new DirectionValueType(orientation));

      // when
      tondeuse.order("A");

      // then
      assertThat(tondeuse.getDirection()).isEqualTo(orientation);
      assertThat(tondeuse.getX()).isEqualTo(expectedX);
      assertThat(tondeuse.getY()).isEqualTo(expectedY);
    }

    @ParameterizedTest(name = "not advance when the tondeuse is at (0,0) and the direction is {0}")
    @ValueSource(strings = {"N", "W", "S", "E"})
    public void advanceLimitField(String orientation) {
      // given
      var tondeuse = new Tondeuse(
          new PositionValueType(0, 0, 0, 0),
          new DirectionValueType(orientation));

      // when
      tondeuse.order("A");

      // then
      assertThat(tondeuse.getDirection()).isEqualTo(orientation);
      assertThat(tondeuse.getX()).isEqualTo(0);
      assertThat(tondeuse.getY()).isEqualTo(0);
    }
  }
}
