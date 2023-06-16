package cs3500.pa05.model;

import cs3500.pa05.json.JsonBujoFile;
import cs3500.pa05.json.JsonWeek;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateNewFile {
  public void createNewFile(Week week, String fileName) throws IOException {
    StringBuilder builder = new StringBuilder();

    JsonWeek jsonWeek = week.weekToJson();
    JsonBujoFile jsonBujoFile = new JsonBujoFile(week.getMaxTasks(), week.getMaxEvents(), jsonWeek);

    builder.append(jsonBujoFile);

    File bujoFile = new File(fileName);
    Scanner input = new Scanner(builder.toString());
    BufferedWriter writer = new BufferedWriter(new FileWriter(bujoFile));

    while (input.hasNextLine()) {
      String line = input.nextLine();

      writer.write(line + System.lineSeparator());
    }

    writer.close();
  }
}
