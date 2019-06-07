package tondeuse.valuetype;

import java.util.Set;
import lombok.Getter;

public class InstructionValueType {

  private static final Set<String> AVAILABLE_INSTRUCTION = Set.of("A", "D", "G");
  @Getter
  private String value;

  public InstructionValueType(String value) {
    if (!AVAILABLE_INSTRUCTION.contains(value)) {
      throw new IllegalArgumentException("the instruction is not right");
    }
    this.value = value;
  }
}
