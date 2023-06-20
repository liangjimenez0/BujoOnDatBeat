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

  private String fileName;
  private String nameForWeek;
  private final List<Day> allDays;
  private List<Task> allTasks;
  private List<Event> allEvents;
  private final int maxTasks;
  private final int maxEvents;

  /**
   * Initializes a week object without a user-defined name.
   *
   * @param maxTasks is the maximum number of tasks a user can have in a week
   * @param maxEvents is the maximum number of events a user can have in a week
   * @param fileName the name of the file this week is
   */
  public Week(int maxTasks, int maxEvents, String fileName) {
    this.allDays = new ArrayList<>();
    this.allTasks = new ArrayList<>();
    this.allEvents = new ArrayList<>();
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
    this.fileName = fileName;
    this.nameForWeek = "welcome to your bullet journal";

    initializeDays(maxTasks, maxEvents);
  }

  /**
   * Initializes all the days to empty days.
   *
   * @param maxTasks is the maximum number of tasks a user can have in a week
   * @param maxEvents is the maximum number of events a user can have in a week
   */
  private void initializeDays(int maxTasks, int maxEvents) {
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
   * Gets the day object from a given day of week.
   *
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
   * Gets the days in this week.
   *
   * @return a list of days from this week
   */
  public List<Day> getDays() {
    return this.allDays;
  }

  public boolean checkDay(String dayName) {
    return
        dayName.equals(DayOfWeek.SUNDAY.name())
        ||dayName.equals(DayOfWeek.MONDAY.name())
        ||dayName.equals(DayOfWeek.TUESDAY.name())
        ||dayName.equals(DayOfWeek.WEDNESDAY.name())
        ||dayName.equals(DayOfWeek.THURSDAY.name())
        ||dayName.equals(DayOfWeek.FRIDAY.name())
        ||dayName.equals(DayOfWeek.SATURDAY.name());
    }

  /**
   * Gets the max tasks for this week.
   *
   * @return the maximum number of tasks this user can have for a week
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Gets the max events for this week.
   *
   * @return the maximum number of events this user can have for a week
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Converts this week to a JsonWeek record.
   *
   * @return a JsonWeek which turns this week to a JsonWeek record
   */
  public JsonWeek weekToJson() {
    List<JsonDay> jsonDays = new ArrayList<>();

    for (Day d : this.allDays) {
      JsonDay jsonDay = d.dayToJson();
      jsonDays.add(jsonDay);
    }

    return new JsonWeek(nameForWeek, jsonDays);
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

  /**
   * Gets all the events in this week.
   *
   * @return the list of all events in this week.
   */
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
    return this.fileName;
  }


  public void setNameForWeek(String s) {
    this.nameForWeek = s;
  }

  public String getNameForWeek() {
    return this.nameForWeek;
  }
}
