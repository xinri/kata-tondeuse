package tondeuse.valuetype;

import field.valuetype.LimitField;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Position {

  private final int x;
  private final int y;

  public Position(int x, int y) {

    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("error position with negative value");
    }

    this.x = x;
    this.y = y;
  }

  public Position moveToNorth() {
    return new Position(x, y + 1);
  }

  public Position moveToEast() {
    return new Position(x + 1, y);
  }

  public Position moveToWest() {
    return new Position(x - 1, y);
  }

  public Position moveToSouth() {
    return new Position(x, y - 1);
  }

  public Position moveToNorth(LimitField limitField) {
    return limitField.isOutOfBounds(x, y + 1) ? this : moveToNorth();
  }

  public Position moveToEast(LimitField limitField) {
    return limitField.isOutOfBounds(x + 1, y) ? this : moveToEast();
  }

  public Position moveToSouth(LimitField limitField) {
    return limitField.isOutOfBounds(x, y - 1) ? this : moveToSouth();
  }

  public Position moveToWest(LimitField limitField) {
    return limitField.isOutOfBounds(x - 1, y) ? this : moveToWest();
  }
}
