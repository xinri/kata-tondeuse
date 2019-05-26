package infrastructure.adapter;

import domain.game.Game;
import domain.game.valuetype.LimitFieldValueType;
import domain.tondeuse.Tondeuse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;

public class GameFileAdapter {

  public GameFileAdapter() {
  }

  public String executeGame(String fileName) throws IOException {

    var file = new File(getClass().getClassLoader().getResource(fileName).getFile());

    if (!file.exists()) {
      throw new IllegalArgumentException("filename cannot be found");
    }

    List<String> lines = FileUtils.readLines(file);

    if (lines.size() < 0) {
      throw new IllegalArgumentException("file is empty");
    }

    var fieldValues = lines.get(0).split(" ");
    if (fieldValues.length != 2) {
      throw new IllegalArgumentException("problem in the max size");
    }
    var game = new Game(
        new LimitFieldValueType(Integer.valueOf(fieldValues[0]),
            Integer.valueOf(fieldValues[1])));

    for (int line = 1; line < lines.size(); line += 2) {
      var tondeusePosition = lines.get(line).split(" ");
      String tondeuseOrders = lines.get(line + 1);

      game.addTondeuse(Integer.valueOf(tondeusePosition[0]), Integer.valueOf(tondeusePosition[1]),
          tondeusePosition[2], tondeuseOrders);
    }

    game.execute();

    return game.getTondeuseList().stream()
        .map(Tondeuse::toString)
        .collect(Collectors.joining(System.lineSeparator()));
  }
}
