package adapter;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FieldFileAdapterShould {

  @Test
  public void nominalCaseFile() throws IOException {
    // given
    var gameFileAdapter = new FieldFileAdapter();

    // when
    var result = gameFileAdapter.executeGame("nominalCase.txt");

    // then
    Assertions.assertThat(result).isEqualTo
        ("1 3 N" + System.lineSeparator() +
            "5 1 E");
  }

}
