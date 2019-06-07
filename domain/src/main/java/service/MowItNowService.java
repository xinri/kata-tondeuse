package service;

import field.GamePlay;
import java.io.IOException;

public class MowItNowService {

  private GamePlay gameAdapter;

  public MowItNowService(GamePlay gameAdapter) {
    this.gameAdapter = gameAdapter;
  }

  public String launchGameFromFile(String filename) throws IOException {
    return gameAdapter.executeGame(filename);
  }
}
