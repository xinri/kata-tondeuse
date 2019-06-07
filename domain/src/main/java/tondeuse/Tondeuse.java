package tondeuse;

import com.google.common.annotations.VisibleForTesting;
import field.valuetype.LimitField;
import tondeuse.valuetype.Direction;
import tondeuse.valuetype.InstructionValueType;
import tondeuse.valuetype.Position;
import tondeuse.valuetype.PositionWithLimits;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringJoiner;
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

  private static final Map<String, BiFunction<Position, LimitField, Position>> moveToNewPosition =
      Map.of("N", (position, limit) -> position.moveToNorth(limit),
          "W", (position, limit) -> position.moveToWest(limit),
          "S", (position, limit) -> position.moveToSouth(limit),
          "E", (position, limit) -> position.moveToEast(limit));

  @Getter
  private Position position;
  @Getter
  private LimitField limitField;
  @Getter
  @Setter
  private String direction;
  private Queue<String> movementQueue;

  public Tondeuse(PositionWithLimits positionWithLimit, Direction direction) {
    this(positionWithLimit, direction, Collections.emptyList());
  }

  public Tondeuse(PositionWithLimits positionWithLimit, Direction direction,
      List<InstructionValueType> listOfInstruction) {

    this.position = positionWithLimit.getPosition();
    this.limitField = positionWithLimit.getLimitField();
    this.direction = direction.getDirection();
    this.movementQueue = new ArrayDeque<>(
        listOfInstruction.stream()
            .map(InstructionValueType::getValue)
            .collect(Collectors.toList()));
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
    position = moveToNewPosition.get(direction).apply(position, limitField);
  }

  @Override
  public String toString() {
    return new StringJoiner(" ")
        .add(String.valueOf(position.getX()))
        .add(String.valueOf(position.getY()))
        .add(direction).toString();
  }
}
