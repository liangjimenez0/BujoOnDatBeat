package cs3500.pa05.model;

import cs3500.pa05.json.JsonDay;
import cs3500.pa05.json.JsonWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a week with fields of days, tasks, events, and max amounts for both
 */
public class Week {

  private String name;
  private final List<Day> allDays;
  private List<Task> allTasks;
  private List<Event> allEvents;
  private final int maxTasks;
  private final int maxEvents;

  /**
   * @param maxTasks is the maximum number of tasks a user can have in a week
   * @param maxEvents is the maximum number of events a user can have in a week
   */
  public Week(int maxTasks, int maxEvents, String name) {
    this.allDays = new ArrayList<>();
    this.allTasks = new ArrayList<>();
    this.allEvents = new ArrayList<>();
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
    this.name = name;

    initializeDays(maxTasks, maxEvents);
  }

  /**
   * @param maxTasks is the maximum number of tasks a user can have in a week
   * @param maxEvents is the maximum number of events a user can have in a week
   */
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


  /**
   * @param day is the given day that is to be returned
   * @return the day from this week's list of days
   */
  public Day getDay(DayOfWeek day) {
    for (Day currDay : allDays) {
      if (currDay.getDayOfWeek() == day) {
        return currDay;
      }
    }
    throw new NoSuchElementException("this day is not in the week");
  }

  /**
   * @return a list of days from this week
   */
  public List<Day> getDays() {
    return this.allDays;
  }

  /**
   * @return the maximum number of tasks this user can have for a week
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * @return the maximum number of events this user can have for a week
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * @return a JsonWeek which turns this week to a JsonWeek record
   */
  public JsonWeek weekToJson() {
    List<JsonDay> jsonDays = new ArrayList<>();

    for (Day d : this.allDays) {
      JsonDay jsonDay = d.dayToJson();
      jsonDays.add(jsonDay);
    }

    return new JsonWeek(jsonDays);
  }

  /**
   * Gets all the tasks in this week.
   *
   * @return the list of all tasks in this week.
   */
  public List<Task> getAllTasks() {
    allTasks = new ArrayList<>();

    for (Day d : this.allDays) {
      allTasks.addAll(d.getTasks());
    }

    return allTasks;
  }

  public List<Event> getAllEvents() {
    allEvents = new ArrayList<>();

    for (Day d : this.allDays) {
      allEvents.addAll(d.getEvents());
    }

    return allEvents;
  }

  /**
   * Gets the name of this week.
   *
   * @return the name of this week
   */
  public String getName() {
    return this.name;
  }
}
