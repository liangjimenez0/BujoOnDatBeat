package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a week with fields of days, tasks, events, and max amounts for both
 */
public class Week {

  private String fileName;
  private String nameForWeek;
  private final List<Day> allDays;
  private List<Task> allTasks;
  private List<Event> allEvents;
  private int maxTasks;
  private int maxEvents;
  private DayOfWeek startDay;
  private String password;

  /**
   * @param maxTasks  is the maximum number of tasks a user can have in a week
   * @param maxEvents is the maximum number of events a user can have in a week
   * @param fileName  the name of the file this week is
   * @param password  is the password to access this week's content
   */
  public Week(int maxTasks, int maxEvents, String fileName, String password) {
    this.allDays = new ArrayList<>();
    this.allTasks = new ArrayList<>();
    this.allEvents = new ArrayList<>();
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
    this.fileName = fileName;
    this.nameForWeek = "welcome to your bullet journal";
    this.password = password;

    initializeDays();
  }

  /**
   * Initializes a week object with a user-defined name, password, and start date.
   *
   * @param maxTasks    is the maximum number of tasks a user can have in a week
   * @param maxEvents   is the maximum number of events a user can have in a week
   * @param fileName    the name of the file this week is
   * @param startDay    what day this week starts on
   * @param password    the key to get into this file
   * @param nameForWeek the custom name for this week
   */
  public Week(int maxTasks, int maxEvents, String fileName, String startDay, String password,
              String nameForWeek) {
    this.allDays = new ArrayList<>();
    this.allTasks = new ArrayList<>();
    this.allEvents = new ArrayList<>();
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
    this.fileName = fileName;
    this.nameForWeek = "welcome to your bullet journal";
    this.startDay = DayOfWeek.valueOf(startDay);
    this.password = password;
    this.nameForWeek = nameForWeek;

    initializeDays();
    initializeWithStartDay(this.startDay);
  }

  /**
   * Initializes all the days to empty days.
   */
  private void initializeDays() {
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
   * Creates a week with custom order.
   *
   * @param start the desired start day
   */
  private void initializeWithStartDay(DayOfWeek start) {
    List<Day> reorderedDays = new ArrayList<>();

    Day startDay = this.allDays.get(this.allDays.indexOf(new Day(start)));

    reorderedDays.add(startDay);

    int index = this.allDays.indexOf(new Day(start));
    while (reorderedDays.size() < 7) {
      if (index == 6) {
        index = 0;
        reorderedDays.add(this.allDays.get(index));
      } else {
        index += 1;
        reorderedDays.add(this.allDays.get(index));
      }
    }
    this.allDays.clear();
    this.allDays.addAll(reorderedDays);
  }


  /**
   * Gets the day object from a given day of week.
   *
   * @param day is the given day that is to be returned
   * @return the day from this week's list of days
   */
  public Day getDay(DayOfWeek day) {
    Day returnDay = this.getDays().get(0);
    for (Day currDay : allDays) {
      if (currDay.getDayOfWeek() == day) {
        returnDay = currDay;
      }
    }
    return returnDay;
  }

  /**
   * Gets the days in this week.
   *
   * @return a list of days from this week
   */
  public List<Day> getDays() {
    return this.allDays;
  }

  /**
   * Checks that the day is a valid day
   *
   * @param dayName the given day to check
   * @return if it is a valid day
   */
  public boolean checkDay(String dayName) {
    return
        dayName.equals(DayOfWeek.SUNDAY.name())
            || dayName.equals(DayOfWeek.MONDAY.name())
            || dayName.equals(DayOfWeek.TUESDAY.name())
            || dayName.equals(DayOfWeek.WEDNESDAY.name())
            || dayName.equals(DayOfWeek.THURSDAY.name())
            || dayName.equals(DayOfWeek.FRIDAY.name())
            || dayName.equals(DayOfWeek.SATURDAY.name());
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


  /**
   * Mutates the name of this week.
   *
   * @param s the new name
   */
  public void setNameForWeek(String s) {
    this.nameForWeek = s;
  }

  /**
   * Gets the current name of this week
   *
   * @return the current week name
   */
  public String getNameForWeek() {
    return this.nameForWeek;
  }

  /**
   * Updates the number of max tasks for this week.
   *
   * @param maxTasks the new number of max tasks
   */
  public void changeMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
  }

  /**
   * Updates the number of max events for this week.
   *
   * @param maxEvents the new number of max events
   */
  public void changeMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
  }

  /**
   * Gets the password for this file.
   *
   * @return the current password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Gets the current start day of this week.
   *
   * @return the start day
   */
  public DayOfWeek getWeekdayStart() {
    return this.startDay;
  }
}
