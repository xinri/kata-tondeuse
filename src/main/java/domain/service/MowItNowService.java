package domain.service;

import infrastructure.adapter.GameFileAdapter;
import java.io.IOException;

public class MowItNowService {

  private GameFileAdapter fileAdapter;

  public MowItNowService(GameFileAdapter fileAdapter) {
    this.fileAdapter = fileAdapter;
  }

  public MowItNowService() {
    this(new GameFileAdapter());
  }

  public String launchGameFromFile(String filename) throws IOException {
    return fileAdapter.executeGame(filename);
  }
}
