package cs3500.pa05.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateNewFile {
  public void createNewFile(Week week, String fileName) {
    try {
      StringBuilder builder = new StringBuilder();

      builder.append("Max tasks: ").append(week.getMaxTasks()).append(System.lineSeparator());
      builder.append("Max events: ").append(week.getMaxEvents()).append(System.lineSeparator());

      for (Day d : week.getDays()) {
        builder.append("Day: ").append(d.getDayOfWeek()).append(System.lineSeparator());

        builder.append("Tasks: ").append(System.lineSeparator());

            for (Task t : d.getTasks()) {
              builder.append(t.taskInStringForm()).append(System.lineSeparator());
            }

        builder.append("Events: ").append(System.lineSeparator());

            for (Event e : d.getEvents()) {
              builder.append(e.eventInStringForm()).append(System.lineSeparator());
            }

      }

      File bujoFile = new File(fileName);
      Scanner input = new Scanner(builder.toString());
      BufferedWriter writer = new BufferedWriter(new FileWriter(bujoFile));

      while (input.hasNextLine()) {
        String line = input.nextLine();

        writer.write(line + System.lineSeparator());
      }

      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
