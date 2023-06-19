package cs3500.pa05.model;

import cs3500.pa05.json.JsonDay;
import cs3500.pa05.json.JsonEvent;
import cs3500.pa05.json.JsonTask;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Day with tasks or events occurring on this day
 */
public class Day {
  private DayOfWeek day;
  private List<Task> tasks;
  private List<Event> events;

  /**
   * Initializes a day
   *
   * @param day is the given weekday Enum
   */
  public Day(DayOfWeek day) {
    this.day = day;
    this.tasks = new ArrayList<>();
    this.events = new ArrayList<>();
  }

  /**
   * Gets the day of the week
   *
   * @return The weekday this day is assigned to
   */
  public DayOfWeek getDayOfWeek() {
    return this.day;
  }

  /**
   * Gets all the tasks in this day
   *
   * @return This day's list of tasks
   */
  public List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Gets all the events in this day
   *
   * @return This day's list of events
   */
  public List<Event> getEvents() {
    return this.events;
  }

  /**
   * Adds a task to this day's tasks
   *
   * @param given Task that is added to this day's list of tasks
   */
  public void addToTask(Task given) {
    this.tasks.add(given);
  }

  /**
   * Adds an event to this day's events
   *
   * @param given Event that is added to this day's list of events
   */
  public void addToEvent(Event given) {
    this.events.add(given);
  }

  /**
   * Converts this day to a JsonDay record.
   *
   * @return A JsonDay record that represents the weekday, list of tasks,
   * and events as a Json record
   */
  public JsonDay dayToJson() {
    List<JsonTask> jsonTasks = new ArrayList<>();
    List<JsonEvent> jsonEvents = new ArrayList<>();

    for (Task t : this.tasks) {
      JsonTask jsonTask = t.taskToJson();
      jsonTasks.add(jsonTask);
    }

    for (Event e : this.events) {
      JsonEvent jsonEvent = e.eventToJson();
      jsonEvents.add(jsonEvent);
    }

    return new JsonDay(this.day.name(), jsonTasks, jsonEvents);
  }


  /**
   * Computes the percentage of tasks completed compared to total tasks.
   *
   * @return the percentage of completed tasks from this day
   */
  public double taskCompletionPercentage() {
    int numOfCompletedTasks = 0;
    for (Task t : this.tasks) {
      if (t.getCompleted()) {
        numOfCompletedTasks += 1;
      }
    }

    if (this.tasks.size() == 0) {
      return 0;
    } else {
      return (numOfCompletedTasks * .10) / (this.tasks.size() * .10);
    }
  }
}
