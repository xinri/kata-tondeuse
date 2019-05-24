package domain.game;

import domain.tondeuse.Tondeuse;
import domain.tondeuse.valuetype.DirectionValueType;
import domain.tondeuse.valuetype.PositionValueType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

  private final int maxX;
  private final int maxY;
  private final List<Tondeuse> listOfTondeuse;

  public Game(int maxX, int maxY) {
    this.maxX = maxX;
    this.maxY = maxY;
    this.listOfTondeuse = new ArrayList<>();
  }

  public void execute() {

  }

  public List<Tondeuse> getTondeuseList() {
    return this.listOfTondeuse;
  }

  public void addTondeuse(int x, int y, String direction, String listOfOrder) {
    listOfTondeuse.add(
        new Tondeuse(new PositionValueType(x, y, maxX, maxY), new DirectionValueType(direction)));
  }
}
