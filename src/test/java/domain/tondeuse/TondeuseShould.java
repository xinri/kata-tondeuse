package domain.tondeuse;

import static org.assertj.core.api.Assertions.assertThat;

import domain.tondeuse.valuetype.Direction;
import domain.tondeuse.valuetype.InstructionValueType;
import domain.tondeuse.valuetype.PositionWithLimits;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TondeuseShould {

  @Test
  public void return_1_1_N_when_to_string_is_called() {
    // given
    var tondeuse = new Tondeuse(
        new PositionWithLimits(1, 1, 5, 5),
        new Direction("N"));

    // when
    var result = tondeuse.toString();

    // then
    assertThat(result).isEqualTo("1 1 N");
  }

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
          new PositionWithLimits(0, 0, 10, 10),
          new Direction(originalDirection));

      // when
      tondeuse.order(orderDirection);

      // then
      assertThat(tondeuse.getDirection()).isEqualTo(expectedDirection);
    }
  }

  @Nested
  @DisplayName("advance to")
  class AdvanceShould {

    @ParameterizedTest(name = "({0}, {1}) when the tondeuse is at (5, 5), {2} oriented")
    @CsvSource({"5, 6, N", "4, 5, W", "5, 4, S", "6, 5, E"})
    public void advanceTo(Integer expectedX, Integer expectedY, String orientation) {
      // given
      var tondeuse = new Tondeuse(
          new PositionWithLimits(5, 5, 10, 10),
          new Direction(orientation));

      // when
      tondeuse.order("A");

      // then
      assertThat(tondeuse.getDirection()).isEqualTo(orientation);
      assertThat(tondeuse.getPosition().getX()).isEqualTo(expectedX);
      assertThat(tondeuse.getPosition().getY()).isEqualTo(expectedY);
    }

    @ParameterizedTest(name = "not advance when the tondeuse is at (0,0) and the direction is {0}")
    @ValueSource(strings = {"N", "W", "S", "E"})
    public void advanceLimitField(String orientation) {
      // given
      var tondeuse = new Tondeuse(
          new PositionWithLimits(0, 0, 0, 0),
          new Direction(orientation));

      // when
      tondeuse.order("A");

      // then
      assertThat(tondeuse.getDirection()).isEqualTo(orientation);
      assertThat(tondeuse.getPosition().getX()).isEqualTo(0);
      assertThat(tondeuse.getPosition().getY()).isEqualTo(0);
    }
  }

  @Nested
  @DisplayName("apply list of movement")
  public class ListOfMovementShould {

    @Test
    @DisplayName("and move to 1 3 N when the tondeuse is at 1 2 N and the list of movment is GAGAGAGAA")
    public void nominalCase1() {
      // given
      var tondeuse = new Tondeuse(
          new PositionWithLimits(1, 2, 5, 5),
          new Direction("N"),
          convertToInstructionValueTypeList("GAGAGAGAA"));

      // when
      for (int i = 0; i < 8; i++) {
        tondeuse.nextStep();
        assertThat(tondeuse.hasFinished()).isFalse();
      }

      tondeuse.nextStep();

      // then
      assertThat(tondeuse)
          .hasFieldOrPropertyWithValue("position.x", 1)
          .hasFieldOrPropertyWithValue("position.y", 3)
          .hasFieldOrPropertyWithValue("direction", "N");
      assertThat(tondeuse.hasFinished()).isTrue();
    }

    @Test
    @DisplayName("and move to 5 1 E when the tondeuse is at 3 3 E and the list of movment is AADAADADDA")
    public void nominalCase2() {
      // given
      var tondeuse = new Tondeuse(
          new PositionWithLimits(3, 3, 5, 5),
          new Direction("E"),
          convertToInstructionValueTypeList("AADAADADDA"));

      // when
      for (int i = 0; i < 9; i++) {
        tondeuse.nextStep();
        assertThat(tondeuse.hasFinished()).isFalse();
      }

      tondeuse.nextStep();

      // then
      assertThat(tondeuse)
          .hasFieldOrPropertyWithValue("position.x", 5)
          .hasFieldOrPropertyWithValue("position.y", 1)
          .hasFieldOrPropertyWithValue("direction", "E");
      assertThat(tondeuse.hasFinished()).isTrue();
    }

    private List<InstructionValueType> convertToInstructionValueTypeList(String movements) {
      return movements.chars()
          .mapToObj(Character::toString)
          .map(InstructionValueType::new)
          .collect(Collectors.toList());
    }
  }
}