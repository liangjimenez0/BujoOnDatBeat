package cs3500.pa05.model;

/**
 * Represents an event that can occur on a week day
 */
public class Event extends Widget {

  private long startTime;
  private int duration;

  /**
   * Initializes an event with a description.
   *
   * @param name        is the name of the event
   * @param day         is the weekday that this event occurs on
   * @param description is a short description of this event
   * @param startTime   is the 24-hour depiction of when this event occurs
   * @param duration    is the number of minutes this event spans
   */
  public Event(String name, DayOfWeek day, String description,
               long startTime, int duration) {
    super(day, name, description);
    this.startTime = startTime;
    this.duration = duration;
  }


  /**
   * Converts this event to a JsonEvent record.
   *
   * @return a JsonEvent with this event's information as it's data
   */
  public JsonEvent eventToJson() {
    return new JsonEvent(this.name, this.description, this.day, this.startTime,
        this.duration);
  }

  /**
   * Determines if this event is the same as another object.
   *
   * @param other the object being compared
   * @return true if the events are equal, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Event)) {
      return false;
    }

    Event that = (Event) other;
    return this.day.equals(that.day) && this.name.equals(that.name)
        && this.description.equals(that.description) && this.duration == that.duration
        && this.startTime == that.startTime;
  }

  /**
   * Gets the start time of this event.
   *
   * @return the event start time.
   */
  public long getStartTime() {
    return this.startTime;
  }

  /**
   * Gets the duration of this event.
   *
   * @return the event duration (in minutes)
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Updates the start time of this event.
   *
   * @param startTime the new start time to be updated.
   */
  public void changeStartTime(Long startTime) {
    this.startTime = startTime;
  }

  /**
   * Updates the duration of this event.
   *
   * @param duration the new duration for the event.
   */
  public void changeDuration(int duration) {
    this.duration = duration;
  }
}
