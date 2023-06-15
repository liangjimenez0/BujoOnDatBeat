package cs3500.pa05.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Week {

  List<Day> allDays;
  List<Task> allTasks;
  List<Event> allEvents;
  int maxTasks;
  int maxEvents;

  public Week(int maxTasks, int maxEvents) {
    this.allDays = new ArrayList<>();
    this.allTasks = new ArrayList<>();
    this.allEvents = new ArrayList<>();
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;

    initializeDays(maxTasks, maxEvents);
  }

  public void initializeDays(int maxTasks, int maxEvents) {
    Day sunday = new Day(DayOfWeek.SUNDAY);
    Day monday = new Day(DayOfWeek.MONDAY);
    Day tuesday = new Day(DayOfWeek.TUESDAY);
    Day wednesday = new Day(DayOfWeek.WEDNESDAY);
    Day thursday = new Day(DayOfWeek.THURSDAY);
    Day friday = new Day(DayOfWeek.FRIDAY);
    Day saturday = new Day(DayOfWeek.SATURDAY);

    allDays.add(sunday);
    allDays.add(monday);
    allDays.add(tuesday);
    allDays.add(wednesday);
    allDays.add(thursday);
    allDays.add(friday);
    allDays.add(saturday);
  }

  public void processWeek(File bujoFile) {
    try {
      Scanner input = new Scanner(bujoFile);

      while (input.hasNextLine()) {
        String line = input.nextLine();

        if (line.contains("Max tasks: ")) {
          int index = line.indexOf(": ");
          maxTasks = Integer.parseInt(line.substring(index));
        } else if (line.contains("Max events: ")) {
          int index = line.indexOf(": ");
          maxTasks = Integer.parseInt(line.substring(index));
        } else if (line.contains("Tasks: ")) {
          while (!line.contains("Events: ")) {
            line = input.nextLine();

            assignTaskValues(line);
          }
        } else if (line.contains("Events: ")){
          line = input.nextLine();
          assignEventValues(line);
        }
      }
      input.close();

    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  private void assignEventValues(String line) {


  }

  private void assignTaskValues(String line) {

    int index = line.indexOf("Day of week: ");
    int indexEnd = line.indexOf(", Completed?:");
    String dayOfWeek = line.substring(index, indexEnd);

    if (dayOfWeek.equals("Sunday")) {
      allDays.get(0).initializeDayTasks(line);
    } else if (dayOfWeek.equals("Monday")) {
      allDays.get(1).initializeDayTasks(line);
    } else if (dayOfWeek.equals("Tuesday")) {
      allDays.get(2).initializeDayTasks(line);
    } else if (dayOfWeek.equals("Wednesday")) {
      allDays.get(3).initializeDayTasks(line);
    } else if (dayOfWeek.equals("Thursday")) {
      allDays.get(4).initializeDayTasks(line);
    } else if (dayOfWeek.equals("Friday")) {
      allDays.get(5).initializeDayTasks(line);
    } else if (dayOfWeek.equals("Saturday")) {
      allDays.get(6).initializeDayTasks(line);
    }

  }
  public List<Day> getDays() {
    return this.allDays;
  }

  public int getMaxTasks() {
    return this.maxTasks;
  }

  public int getMaxEvents() {
    return this.maxEvents;
  }
}
