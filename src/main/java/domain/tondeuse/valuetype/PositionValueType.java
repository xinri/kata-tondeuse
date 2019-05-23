package domain.tondeuse.valuetype;

import lombok.Getter;

@Getter
public class PositionValueType {

  private final int x;
  private final int y;
  private final int maxX;
  private final int maxY;

  public PositionValueType(int x, int y, int maxX, int maxY) {

    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("error position with negative value");
    } else if (x > maxX || y > maxY) {
      throw new IllegalArgumentException("error position outside the field");
    }

    this.x = x;
    this.y = y;
    this.maxX = maxX;
    this.maxY = maxY;
  }
}
