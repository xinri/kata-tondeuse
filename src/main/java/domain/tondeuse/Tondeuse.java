package domain.tondeuse;

import com.google.common.annotations.VisibleForTesting;
import domain.tondeuse.valuetype.DirectionValueType;
import domain.tondeuse.valuetype.InstructionValueType;
import domain.tondeuse.valuetype.PositionValueType;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

/**
 * The aggregate Tondeuse can be split into other part for single responsibility
 */
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
      (variable, limit) -> variable + 1 <= limit ? variable + 1 : variable;

  private static final BiFunction<Integer, Integer, Integer> MOVE_NEGATIVE_FIELD =
      (variable, limit) -> variable - 1 >= 0 ? variable - 1 : variable;

  @Getter @Setter
  private int x;
  @Getter @Setter
  private int y;
  private int maxX;
  private int maxY;
  @Getter @Setter
  private String direction;
  private Queue<String> movementQueue;

  public Tondeuse(PositionValueType position, DirectionValueType direction) {
    this(position, direction, Collections.emptyList());
  }

  public Tondeuse(PositionValueType position, DirectionValueType direction,
      List<InstructionValueType> listOfInstruction) {

    this(position.getX(), position.getY(), position.getMaxX(), position.getMaxY(),
        direction.getDirection(),
        listOfInstruction.stream()
            .map(InstructionValueType::getValue)
            .collect(Collectors.toList()));
  }

  private Tondeuse(int x, int y, int maxX, int maxY, String direction, List<String> listOfMouvement) {
    this.x = x;
    this.y = y;
    this.maxX = maxX;
    this.maxY = maxY;
    this.direction = direction;
    this.movementQueue = new ArrayDeque<>(listOfMouvement);
  }

  public void nextStep() {
    order(movementQueue.remove());
  }

  public boolean hasFinished() {
    return movementQueue.isEmpty();
  }

  @VisibleForTesting
  void order(String instruction) {
    if ("A".equals(instruction)) {
      move();
    } else {
      turn(instruction);
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
