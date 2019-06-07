package tondeuse.valuetype;

import field.valuetype.LimitField;
import lombok.Getter;

@Getter
public class PositionWithLimits {

  private final Position position;
  private final LimitField limitField;

  public PositionWithLimits(final Position position, final LimitField limitField) {

    if (position.getX() > limitField.getLimitMaxX() || position.getY() > limitField.getLimitMaxY()) {
      throw new IllegalArgumentException("error position outside the field");
    }

    this.position = position;
    this.limitField = limitField;
  }

  public PositionWithLimits(int x, int y, int maxX, int maxY) {
    this(new Position(x, y), new LimitField(maxX, maxY));
  }
}
