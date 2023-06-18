package cs3500.pa05.model;

/**
 * Represents the abstract class of a widget in a Java Journal
 */
public abstract class Widget {

  public DayOfWeek day;
  public String nameOfTask;
  public String description;

  /**
   * @param day is the week day that this occurs on
   * @param nameOfTask is the name of this task
   * @param description is a short description of this widget
   */
  public Widget(String nameOfTask, DayOfWeek day, String description) {
    this.day = day;
    this.nameOfTask = nameOfTask;
    this.description = description;
  }

  public Widget(String nameOfTask, DayOfWeek day) {
    this.day = day;
    this.nameOfTask = nameOfTask;
  }

  public DayOfWeek getDay() {
    return this.day;
  }

  public String getName() {
    return this.nameOfTask;
  }
}
