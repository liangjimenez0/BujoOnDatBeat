package cs3500.pa05.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the class that creates a new Bujo file based on a week's information
 */
public class CreateNewFile {
  /**
   * Initializes an object that can create a new file.
   *
   * @param week     is the given week that is to be converted to a Bujo file
   * @param fileName is the given file name of where the Bujo file contents will be outputted in
   * @throws IOException when this method gets interrupted
   */
  public void createNewFile(Week week, String fileName) throws IOException {
    StringBuilder builder = new StringBuilder();

    JsonWeek jsonWeek = week.weekToJson();
    JsonBujoFile jsonBujoFile =
        new JsonBujoFile(week.getMaxTasks(), week.getMaxEvents(), week.getPassword(), jsonWeek);

    builder.append(JsonUtils.serializeRecord(jsonBujoFile).toPrettyString());

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
