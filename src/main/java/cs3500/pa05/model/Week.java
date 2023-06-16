package cs3500.pa05.model;

import cs3500.pa05.json.JsonDay;
import cs3500.pa05.json.JsonWeek;
import java.util.ArrayList;
import java.util.List;

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

  public List<Day> getDays() {
    return this.allDays;
  }

  public int getMaxTasks() {
    return this.maxTasks;
  }

  public int getMaxEvents() {
    return this.maxEvents;
  }

  public JsonWeek weekToJson() {
    List<JsonDay> jsonDays = new ArrayList<>();

    for (Day d : this.allDays) {
      JsonDay jsonDay = d.dayToJson();
      jsonDays.add(jsonDay);
    }

    return new JsonWeek(jsonDays);
  }
}
