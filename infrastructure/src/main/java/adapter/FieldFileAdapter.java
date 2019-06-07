package adapter;

import field.Field;
import field.GamePlay;
import field.valuetype.LimitField;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import tondeuse.Tondeuse;

public class FieldFileAdapter implements GamePlay {

  private static final String SPACE_SEPARATOR = " ";

  public FieldFileAdapter() {
  }

  public String executeGame(String fileName) throws IOException {

    List<String> lines = retrieveFileLines(fileName);

    Field game = buildFieldFromTheFirstLine(lines);

    buildTondeuseListFromLines(lines, game);

    game.execute();

    return buildResult(game);
  }

  private List<String> retrieveFileLines(String fileName) throws IOException {
    var resources = getClass().getClassLoader().getResource(fileName);

    if (resources == null) {
      throw new IllegalArgumentException("filename cannot be found");
    }

    var file = new File(resources.getFile());

    List<String> lines = FileUtils.readLines(file);

    if (lines.size() < 0) {
      throw new IllegalArgumentException("file is empty");
    }
    return lines;
  }


  private Field buildFieldFromTheFirstLine(List<String> lines) {
    var fieldValues = lines.get(0).split(SPACE_SEPARATOR);
    if (fieldValues.length != 2) {
      throw new IllegalArgumentException("problem in the max size");
    }
    return new Field(
        new LimitField(Integer.valueOf(fieldValues[0]),
            Integer.valueOf(fieldValues[1])));
  }

  private void buildTondeuseListFromLines(List<String> lines, Field game) {
    for (int line = 1; line < lines.size(); line += 2) {
      var tondeusePosition = lines.get(line).split(SPACE_SEPARATOR);
      String tondeuseOrders = lines.get(line + 1);

      game.addTondeuse(Integer.valueOf(tondeusePosition[0]), Integer.valueOf(tondeusePosition[1]),
          tondeusePosition[2], tondeuseOrders);
    }
  }

  private String buildResult(Field game) {
    return game.getTondeuseList().stream()
        .map(Tondeuse::toString)
        .collect(Collectors.joining(System.lineSeparator()));
  }

}
