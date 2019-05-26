package domain.game;

import com.google.common.collect.ImmutableList;
import domain.game.valuetype.LimitFieldValueType;
import domain.tondeuse.Tondeuse;
import domain.tondeuse.valuetype.DirectionValueType;
import domain.tondeuse.valuetype.InstructionValueType;
import domain.tondeuse.valuetype.PositionValueType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Game {

  private final int maxX;
  private final int maxY;
  private final List<Tondeuse> listOfTondeuse;

  public Game(LimitFieldValueType limitField) {
    this.maxX = limitField.getLimitX();
    this.maxY = limitField.getLimitY();
    this.listOfTondeuse = new ArrayList<>();
  }

  public void execute() {
    while(!listOfActiveTondeuse().isEmpty()) {
      listOfActiveTondeuse().forEach(Tondeuse::nextStep);
    }
  }

  public List<Tondeuse> getTondeuseList() {
    return ImmutableList.copyOf(this.listOfTondeuse);
  }

  public void addTondeuse(int x, int y, String direction, String orders) {
    listOfTondeuse.add(
        new Tondeuse(new PositionValueType(x, y, maxX, maxY),
            new DirectionValueType(direction),
            convertToInstructionValueTypeList(orders)));
  }

  private List<Tondeuse> listOfActiveTondeuse() {
    return listOfTondeuse.stream()
        .filter(Predicate.not(Tondeuse::hasFinished))
        .collect(Collectors.toList());
  }

  private List<InstructionValueType> convertToInstructionValueTypeList(String movements) {
    return movements.chars()
        .mapToObj(Character::toString)
        .map(InstructionValueType::new)
        .collect(Collectors.toList());
  }
}
