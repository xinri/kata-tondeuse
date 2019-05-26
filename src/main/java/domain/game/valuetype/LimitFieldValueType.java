package domain.game.valuetype;

import lombok.Getter;

@Getter
public class LimitFieldValueType {

  private final int limitX;
  private final int limitY;

  public LimitFieldValueType(int limitX, int limitY) {

    if (limitX < 0 || limitY < 0) {
      throw new IllegalArgumentException("the limit value cannot be negative");
    }
    this.limitX = limitX;
    this.limitY = limitY;
  }
}
