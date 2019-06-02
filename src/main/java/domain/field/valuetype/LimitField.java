package domain.field.valuetype;

import lombok.Getter;

@Getter
public class LimitField {

  private static final int limitMinX = 0;
  private static final int limitMinY = 0;
  private final int limitMaxX;
  private final int limitMaxY;

  public LimitField(int limitMaxX, int limitMaxY) {

    if (limitMaxX < 0 || limitMaxY < 0) {
      throw new IllegalArgumentException("the limit value cannot be negative");
    }
    this.limitMaxX = limitMaxX;
    this.limitMaxY = limitMaxY;
  }
}
