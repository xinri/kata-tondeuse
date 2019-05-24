package domain.tondeuse;

import domain.tondeuse.valuetype.DirectionValueType;
import domain.tondeuse.valuetype.InstructionValueType;
import domain.tondeuse.valuetype.PositionValueType;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import lombok.Getter;
import lombok.Setter;

public class Tondeuse {

  private static final Map<String, String> MAP_OF_TURN_D = Map.of(
      "N", "E",
      "W", "N",
      "S", "W",
      "E", "S");
  private static final Map<String, String> MAP_OF_TURN_G = Map.of(
      "N", "W",
      "W", "S",
      "S", "E",
      "E", "N");

  private static final Map<String, Function<String, String>> MAP_OF_TURN =
      Map.of("D", MAP_OF_TURN_D::get, "G", MAP_OF_TURN_G::get);

  private static final BiFunction<Integer, Integer, Integer> MOVE_POSITIVE_FIELD =
      (variable, limit) -> variable + 1 < limit ? variable + 1 : variable;

  private static final BiFunction<Integer, Integer, Integer> MOVE_NEGATIVE_FIELD =
      (variable, limit) -> variable - 1 > 0 ? variable - 1 : variable;

  @Getter
  @Setter
  private int x;
  @Getter
  @Setter
  private int y;
  private int maxX;
  private int maxY;
  @Getter
  @Setter
  private String direction;

  public Tondeuse(PositionValueType position, DirectionValueType direction) {
    this(position.getX(), position.getY(), position.getMaxX(), position.getMaxY(),
        direction.getDirection());
  }

  private Tondeuse(int x, int y, int maxX, int maxY, String direction) {
    this.x = x;
    this.y = y;
    this.maxX = maxX;
    this.maxY = maxY;
    this.direction = direction;
  }

  public void order(InstructionValueType instruction) {
    if ("A".equals(instruction.getValue())) {
      move();
    } else {
      turn(instruction.getValue());
    }
  }

  private void turn(String instructionValue) {
    setDirection(
        MAP_OF_TURN.get(instructionValue)
            .apply(this.direction));
  }

  private void move() {
    switch (direction) {
      case "N":
        setY(MOVE_POSITIVE_FIELD.apply(y, maxY));
        break;
      case "W":
        setX(MOVE_NEGATIVE_FIELD.apply(x, maxX));
        break;
      case "S":
        setY(MOVE_NEGATIVE_FIELD.apply(y, maxY));
        break;
      case "E":
        setX(MOVE_POSITIVE_FIELD.apply(x, maxX));
        break;
    }
  }
}
