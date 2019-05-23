import java.util.HashMap;
import lombok.Getter;

public class Tondeuse {

  @Getter
  private int x;
  @Getter
  private int y;
  private int maxX;
  private int maxY;
  @Getter
  private String direction;

  public Tondeuse(PositionValueType position, DirectionValueType direction) {
    this(position.getX(), position.getY(), position.getMaxX(), position.getMaxY(),
        direction.getDirection());
  }

  private Tondeuse(int x, int y, int maxX, int maxY, String direction) {
    this.x = x;
    this.y = y;
    this.maxX = maxX;
    this.maxY = maxY;
    this.direction = direction;
  }

  public void order(InstructionValueType instruction) {
    if ("A".equals(instruction.getValue())) {
      move();
    } else {
      turn(instruction.getValue());
    }
  }

  private void turn(String instructionValue) {
    if ("N".equals(direction)) {
      switch (instructionValue) {
        case "D":
          this.direction = "E";
          break;
        case "G":
          this.direction = "W";
          break;
      }
    } else if ("W".equals(direction)) {
      switch (instructionValue) {
        case "D":
          this.direction = "N";
          break;
        case "G":
          this.direction = "S";
          break;
      }
    } else if ("S".equals(direction)) {
      switch (instructionValue) {
        case "D":
          this.direction = "W";
          break;
        case "G":
          this.direction = "E";
          break;
      }
    } else if ("E".equals(direction)) {
      switch (instructionValue) {
        case "D":
          this.direction = "S";
          break;
        case "G":
          this.direction = "N";
          break;
      }
    }
  }

  private void move() {
    switch (direction) {
      case "N":
        if (y + 1 < maxY) {
          y++;
        }
        break;
      case "W":
        if (x - 1 > 0) {
          x--;
        }
        break;
      case "S":
        if (y - 1 > 0) {
          y--;
        }
        break;
      case "E":
        if (x + 1 < maxX) {
          x++;
        }
        break;
    }
  }
}
