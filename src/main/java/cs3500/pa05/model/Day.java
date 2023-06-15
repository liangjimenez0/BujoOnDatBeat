package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day {

  DayOfWeek day;
  List<Task> tasks;
  List<Event> events;

  public Day(DayOfWeek day) {
    this.day = day;
    this.tasks = new ArrayList<>();
    this.events = new ArrayList<>();
  }


  public DayOfWeek getDayOfWeek() {
    return this.day;
  }

  public List<Task> getTasks() {
    return this.tasks;
  }

  public List<Event> getEvents() {
    return this.events;
  }

  public void initializeDayTasks(String line) {
    Pattern pattern = Pattern.compile("\\[\\[(.*?)]]");


    int indexStartName = line.indexOf("Name: ");
    int indexEndName = line.indexOf(", Description: ");
    String name = line.substring(indexStartName, indexEndName);

    int indexStartName = line.indexOf("Description: ");
    int indexEndName = line.indexOf(", Description: ");
    String name = line.substring(indexStartName, indexEndName);


    DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayOfWeekString);

    Task newTask = new Task(dayOfWeek, description, name);

  }
}
