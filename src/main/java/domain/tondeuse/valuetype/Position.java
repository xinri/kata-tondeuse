package domain.tondeuse.valuetype;

import lombok.Getter;

@Getter
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
}
