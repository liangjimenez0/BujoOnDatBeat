package cs3500.pa05.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

  File file;
  Week week;
  Scanner input;

  ReadFile(File file) {
    this.file = file;

    try {
      this.input = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public Week processFile() {
    createWeek();
    setWidgets();

    return this.week;
  }

  private void createWeek() {
    String maxTasks = input.nextLine();
    int indexTask = maxTasks.indexOf(": ") + 2;
    int maxTasksNum = Integer.parseInt(maxTasks.substring(indexTask));

    String maxEvents = input.nextLine();
    int indexEvent = maxTasks.indexOf(": ") + 2;
    int maxEventsNum = Integer.parseInt(maxEvents.substring(indexEvent));

    this.week = new Week(maxTasksNum, maxEventsNum);
  }

  private void setWidgets() {
    while (input.hasNextLine()) {
      String line = input.nextLine();

      if (line.contains("Day: Sunday")) {
        initializeWholeDay(0, "Monday", line);
      } else if (line.contains("Day: Monday")) {
        initializeWholeDay(1, "Tuesday", line);
      } else if (line.contains("Day: Tuesday")) {
        initializeWholeDay(2, "Wednesday", line);
      } else if (line.contains("Day: Wednesday")) {
        initializeWholeDay(3, "Thursday", line);
      } else if (line.contains("Day: Thursday")) {
        initializeWholeDay(4, "Friday", line);
      } else if (line.contains("Day: Friday")) {
        initializeWholeDay(5, "Saturday", line);
      } else if (line.contains("Day: Saturday")) {
        initializeWholeDay(6, "Sunday", line);
      }

    }
  }

  private void initializeWholeDay(int dayInList, String nextDay, String line) {
    while (!line.contains("Day: " + nextDay)) {
      line = input.nextLine();
      while (!line.contains("Events: ")) {
        line = input.nextLine();
        initializeDayTasks(line, week.getDays().get(dayInList));
      }
      initializeDayEvents(line, week.getDays().get(dayInList));
    }
  }

  private void initializeDayTasks(String line, Day day) {
    int indexStartName = line.indexOf("Name: ") + 6;
    int indexEndName = line.indexOf(", Description: ");
    String name = line.substring(indexStartName, indexEndName);

    int indexStartDescription = line.indexOf("Description: ") + 13;
    int indexEndDescription = line.indexOf(", Day of week: ");
    String description = line.substring(indexStartDescription, indexEndDescription);

    int indexStartDOW = line.indexOf("Day of week: ") + 13;
    int indexEndDOW = line.indexOf(", Completed?: ");
    String dow = line.substring(indexStartDOW, indexEndDOW);
    DayOfWeek dayOfWeek = DayOfWeek.valueOf(dow);

    int indexStartCompleted = line.indexOf("Completed?: ") + 12;
    String completed = line.substring(indexStartCompleted);
    boolean completedBoolean = false;

    if (completed.equals("true")) {
      completedBoolean = true;
    }

    Task task = new Task(name, dayOfWeek, description, completedBoolean);

    day.getTasks().add(task);
  }

  private void initializeDayEvents(String line, Day day) {
    int indexStartName = line.indexOf("Name: ") + 6;
    int indexEndName = line.indexOf(", Description: ");
    String name = line.substring(indexStartName, indexEndName);

    int indexStartDescription = line.indexOf("Description: ") + 13;
    int indexEndDescription = line.indexOf(", Day of week: ");
    String description = line.substring(indexStartDescription, indexEndDescription);

    int indexStartDOW = line.indexOf("Day of week: ") + 13;
    int indexEndDOW = line.indexOf(", Start time: ");
    String dow = line.substring(indexStartDOW, indexEndDOW);
    DayOfWeek dayOfWeek = DayOfWeek.valueOf(dow);

    int indexStartStartTime = line.indexOf("Start time: ") + 12;
    int indexEndStartTime = line.indexOf(", Duration: ");
    String startTime = line.substring(indexStartStartTime, indexEndStartTime);
    long startTimeLong = Long.parseLong(startTime);

    int indexStartDuration = line.indexOf("Duration: ") + 10;
    String duration = line.substring(indexStartDuration);
    int durationInt = Integer.parseInt(duration);

    Event event = new Event(name, dayOfWeek, description, startTimeLong, durationInt);

    day.getEvents().add(event);
  }
}

