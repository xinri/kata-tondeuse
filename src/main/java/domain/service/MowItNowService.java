package domain.service;

import infrastructure.adapter.FieldFileAdapter;
import java.io.IOException;

public class MowItNowService {

  private FieldFileAdapter fileAdapter;

  public MowItNowService(FieldFileAdapter fileAdapter) {
    this.fileAdapter = fileAdapter;
  }

  public MowItNowService() {
    this(new FieldFileAdapter());
  }

  public String launchGameFromFile(String filename) throws IOException {
    return fileAdapter.executeGame(filename);
  }
}
