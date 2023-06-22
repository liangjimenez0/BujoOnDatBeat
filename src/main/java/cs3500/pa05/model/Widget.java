package cs3500.pa05.model;

/**
 * Represents the abstract class of a widget in a Java Journal
 */
public abstract class Widget {

  /**
   * Represents that week day that this widget occurs on
   */
  protected DayOfWeek day;

  /**
   * Represents the name of this widget
   */
  protected String name;

  /**
   * Represents the description of this widget detailing its specifics
   */
  protected String description;

  /**
   * Initializes a widget object with a description.
   *
   * @param day is the week day that this occurs on
   * @param name is the name of this task
   * @param description is a short description of this widget
   */
  public Widget(DayOfWeek day, String name, String description) {
    this.day = day;
    this.name = name;
    this.description = description;
  }

  /**
   * Initializes a widget object without a description.
   *
   * @param day is the week day that this occurs on
   * @param name is the name of this task
   */
  public Widget(DayOfWeek day, String name) {
    this.day = day;
    this.name = name;
  }

  /**
   * Gets the day of this widget.
   *
   * @return the day this widget is associated with
   */
  public DayOfWeek getDay() {
    return this.day;
  }

  /**
   * Gets the name of this widget.
   *
   * @return the name of this widget
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the description of this widget
   *
   * @return the description
   */
  public String getDescription() {
    if (this.description != null) {
      return this.description;
    } else {
      return "";
    }
  }

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
