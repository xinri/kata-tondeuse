package domain.tondeuse;

import com.google.common.annotations.VisibleForTesting;
import domain.field.valuetype.LimitField;
import domain.tondeuse.valuetype.Direction;
import domain.tondeuse.valuetype.InstructionValueType;
import domain.tondeuse.valuetype.Position;
import domain.tondeuse.valuetype.PositionWithLimits;
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

  private static final BiFunction<Integer, Integer, Integer> MOVE_POSITIVE_FIELD =
      (variable, limit) -> variable + 1 <= limit ? variable + 1 : variable;

  private static final BiFunction<Integer, Integer, Integer> MOVE_NEGATIVE_FIELD =
      (variable, limit) -> variable - 1 >= 0 ? variable - 1 : variable;

  @Getter
  private Position position;
  @Getter
  private LimitField limitField;

  private int maxX;
  private int maxY;
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
    switch (direction) {
      case "N":
        position.setY(MOVE_POSITIVE_FIELD.apply(position.getY(), limitField.getLimitMaxY()));
        break;
      case "W":
        position.setX(MOVE_NEGATIVE_FIELD.apply(position.getX(), limitField.getLimitMaxX()));
        break;
      case "S":
        position.setY(MOVE_NEGATIVE_FIELD.apply(position.getY(), limitField.getLimitMaxY()));
        break;
      case "E":
        position.setX(MOVE_POSITIVE_FIELD.apply(position.getX(), limitField.getLimitMaxX()));
        break;
    }
  }

  @Override
  public String toString() {
    return new StringJoiner(" ")
        .add(String.valueOf(position.getX()))
        .add(String.valueOf(position.getY()))
        .add(direction).toString();
  }
}
