package domain.tondeuse.valuetype;

import domain.field.valuetype.UpperRightLimitField;
import lombok.Getter;

@Getter
public class PositionWithLimits {

  private final Position position;
  private final UpperRightLimitField limitField;

  public PositionWithLimits(final Position position, final UpperRightLimitField limitField) {

    if (position.getX() > limitField.getLimitX() || position.getY() > limitField.getLimitY()) {
      throw new IllegalArgumentException("error position outside the field");
    }

    this.position = position;
    this.limitField = limitField;
  }

  public PositionWithLimits(int x, int y, int maxX, int maxY) {
    this(new Position(x, y), new UpperRightLimitField(maxX, maxY));
  }

  public int getX() {
    return position.getX();
  }

  public int getY() {
    return position.getY();
  }

  public int getMaxX() {
    return limitField.getLimitX();
  }

  public int getMaxY() {
    return limitField.getLimitY();
  }
}
