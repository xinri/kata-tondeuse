package domain.tondeuse.valuetype;

import lombok.Getter;
import lombok.Setter;

@Getter
// FIXME : remove the setter by including movement => must avoid variable
// FIXME : modification on setter for immutability
@Setter
public class Position {

  // FIXME : change thse two variables into a final one
  private int x;
  private int y;

  public Position(int x, int y) {

    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("error position with negative value");
    }

    this.x = x;
    this.y = y;
  }
}
