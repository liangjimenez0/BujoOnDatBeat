package cs3500.pa05.model;

/**
 * Represents the abstract class of a widget in a Java Journal
 */
public abstract class Widget {

  public DayOfWeek day;
  public String name;
  public String description;

  /**
   * @param day is the week day that this occurs on
   * @param name is the name of this task
   * @param description is a short description of this widget
   */
  public Widget(DayOfWeek day, String name, String description) {
    this.day = day;
    this.name = name;
    this.description = description;
  }

  public Widget(DayOfWeek day, String name) {
    this.day = day;
    this.name = name;
  }

  public DayOfWeek getDay() {
    return this.day;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() { return this.description; }

  /**
   * Updates the name of this widget.
   *
   * @param name the new name
   */
  public void changeName(String name) {
    this.name = name;
  }

  /**
   * Updates the day of this widget.
   *
   * @param day the new day
   */
  public void changeDay(DayOfWeek day) {
    this.day = day;
  }

  /**
   * Updates the description of this widget.
   *
   * @param description the new description
   */
  public void changeDescription(String description) {
    this.description = description;
  }
}
