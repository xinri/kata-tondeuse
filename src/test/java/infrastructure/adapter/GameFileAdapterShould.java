package infrastructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class GameFileAdapterShould {

  @Test
  public void nominalCaseFile() throws IOException {
    // given
    var gameFileAdapter = new GameFileAdapter();

    // when
    var result = gameFileAdapter.executeGame("nominalCase.txt");

    // then
    assertThat(result).isEqualTo
        ("1 3 N" + System.lineSeparator() +
            "5 1 E");
  }

}
