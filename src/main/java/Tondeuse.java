public class Tondeuse {

  private int x;
  private int y;
  private String direction;

  public Tondeuse(PositionValueType position, DirectionValueType direction) {
    this(position.getX(), position.getY(), direction.getDirection());
  }

  private Tondeuse(int x, int y, String direction) {
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  public void move(String order) {
    if ("A".equals(order)) {
      switch (direction) {
        case "N":
          y++;
          break;
        case "W":
          x--;
          break;
        case "S":
          y--;
          break;
        case "E":
          x++;
          break;
      }
    } else {
      if ("N".equals(direction)) {
        switch (order) {
          case "D":
            this.direction = "E";
            break;
          case "G":
            this.direction = "W";
            break;
        }
      } else if ("W".equals(direction)) {
        switch (order) {
          case "D":
            this.direction = "N";
            break;
          case "G":
            this.direction = "S";
            break;
        }
      } else if ("S".equals(direction)) {
        switch (order) {
          case "D":
            this.direction = "W";
            break;
          case "G":
            this.direction = "E";
            break;
        }
      } else if ("E".equals(direction)) {
        switch (order) {
          case "D":
            this.direction = "S";
            break;
          case "G":
            this.direction = "N";
            break;
        }
      }
    }
  }

  public String getDirection() {
    return direction;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
