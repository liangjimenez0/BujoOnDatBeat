package cs3500.pa05.model;

import cs3500.pa05.json.JsonEvent;
import cs3500.pa05.json.JsonTask;

/**
 * Represents an event that can occur on a week day
 */
public class Event extends Widget {

  private long startTime;
  private int duration;

  /**
   * @param nameOfEvent is the name of the task
   * @param day         is the weekday that this task occurs on
   * @param description is a short description of this task
   * @param startTime   is the 24-hour depiction of when this event occurs
   * @param duration    is the number of minutes this event spans
   */
  public Event(String nameOfEvent, DayOfWeek day, String description,
               long startTime, int duration) {
    super(day, description, nameOfEvent);
    this.startTime = startTime;
    this.duration = duration;
  }

  public Event(String nameOfEvent, DayOfWeek day, long startTime, int duration) {
    super(day, nameOfEvent);
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * @return a JsonEvent with this event's information as it's data
   */
  public JsonEvent eventToJson() {
    return new JsonEvent(this.nameOfTask, this.description, this.day, this.startTime,
        this.duration);
  }

}
