package application;

import domain.service.MowItNowService;
import infrastructure.adapter.FieldFileAdapter;
import java.io.IOException;

public class FileLauncher {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      throw new IllegalArgumentException("must have one file name as argument");
    }

    var mowItNowService = new MowItNowService(new FieldFileAdapter());

    System.out.println(mowItNowService.launchGameFromFile(args[0]));
  }
}
