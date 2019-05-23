import java.util.Set;
import lombok.Getter;

@Getter
public class DirectionValueType {

  private static final Set<String> AVAILABLE_DIRECTION = Set.of("N", "S", "W", "E");
  private String direction;

  public DirectionValueType(String direction) {
    if (!AVAILABLE_DIRECTION.contains(direction)) {
      throw new IllegalArgumentException("the direction is wrong");
    }
    this.direction = direction;
  }
}
