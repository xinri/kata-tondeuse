package field;

import com.google.common.collect.ImmutableList;
import field.valuetype.LimitField;
import tondeuse.Tondeuse;
import tondeuse.valuetype.Direction;
import tondeuse.valuetype.InstructionValueType;
import tondeuse.valuetype.PositionWithLimits;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Field {

  private final int maxX;
  private final int maxY;
  private final List<Tondeuse> listOfTondeuse;

  public Field(LimitField limitField) {
    this.maxX = limitField.getLimitMaxX();
    this.maxY = limitField.getLimitMaxY();
    this.listOfTondeuse = new ArrayList<>();
  }

  public void execute() {
    while (!listOfActiveTondeuse().isEmpty()) {
      listOfActiveTondeuse().forEach(Tondeuse::nextStep);
    }
  }

  public List<Tondeuse> getTondeuseList() {
    return ImmutableList.copyOf(this.listOfTondeuse);
  }

  public void addTondeuse(int x, int y, String direction, String orders) {
    listOfTondeuse.add(
        new Tondeuse(new PositionWithLimits(x, y, maxX, maxY),
            new Direction(direction),
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
