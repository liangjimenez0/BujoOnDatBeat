package cs3500.pa05.model;

public class Event extends Widget {

  private long startTime;
  private int duration;

  public Event(String nameOfTask, DayOfWeek day, String description, long startTime, int duration) {
    super(day, description, nameOfTask);
    this.startTime = startTime;
    this.duration = duration;
  }

  public StringBuilder eventInStringForm() {
    StringBuilder builder = new StringBuilder();

    builder.append("Name: ").append(this.nameOfTask).append(", ");
    builder.append("Description: ").append(this.description).append(", ");
    builder.append("Day of week: ").append(this.day).append(", ");
    builder.append("Start time: ").append(this.startTime).append(", ");
    builder.append("Duration: ").append(this.duration).append(", ");

    return builder;
  }
}
