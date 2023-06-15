package cs3500.pa05.model;

public abstract class Widget {

  public DayOfWeek day;
  public String nameOfTask;
  public String description;

  public Widget(DayOfWeek day, String nameOfTask, String description) {
    this.day = day;
    this.nameOfTask = nameOfTask;
    this.description = description;
  }

}
