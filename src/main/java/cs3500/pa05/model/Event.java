package cs3500.pa05.model;

import cs3500.pa05.json.JsonEvent;
import cs3500.pa05.json.JsonTask;

/**
 * Represents an event that can occur on a week day
 */
public class Event extends Widget {

  private long startTime;
  private int duration;

  public Event(String nameOfTask, DayOfWeek day, String description, long startTime, int duration) {
    super(day, description, nameOfTask);
    this.startTime = startTime;
    this.duration = duration;
  }

  public JsonEvent eventToJson() {
    return new JsonEvent(this.nameOfTask, this.description, this.day, this.startTime,
        this.duration);
  }
}
