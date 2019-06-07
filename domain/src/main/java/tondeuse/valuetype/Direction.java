package tondeuse.valuetype;

import java.util.Set;
import lombok.Getter;

@Getter
public class Direction {

  private static final Set<String> AVAILABLE_DIRECTION = Set.of("N", "S", "W", "E");
  private String direction;

  public Direction(String direction) {
    if (!AVAILABLE_DIRECTION.contains(direction)) {
      throw new IllegalArgumentException("the direction is wrong");
    }
    this.direction = direction;
  }
}
