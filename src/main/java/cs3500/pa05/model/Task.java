package cs3500.pa05.model;

/**
 * Represents a task that occurs on a day with a name and description of the task
 */
public class Task extends Widget {
  private boolean completed;

  /**
   * Initializes a task with a description.
   *
   * @param name        is the name of this task
   * @param day         is the day this task occurs on
   * @param description is a short description of what this task is
   */
  public Task(String name, DayOfWeek day, String description) {
    super(day, name, description);
    this.completed = false;
  }


  /**
   * Initializes a task with information about completed or not with a description.
   *
   * @param name        is the name of this task
   * @param day         is the day this task occurs on
   * @param description is a short description of what this task is
   * @param completed   is a boolean value of whether this task is completed or not
   */
  public Task(String name, DayOfWeek day, String description, boolean completed) {
    super(day, name, description);
    this.completed = completed;
  }

  /**
   * Initializes a task with information about completed or not without a description.
   *
   * @param name        is the name of this task
   * @param day         is the day this task occurs on
   * @param completed   is a boolean value of whether this task is completed or not
   */
  public Task(String name, DayOfWeek day, boolean completed) {
    super(day, name);
    this.completed = completed;
  }

  /**
   * Converts this task to a JsonTask record.
   *
   * @return a JsonTask that is this task turned to JsonTask record
   */
  public JsonTask taskToJson() {
    return new JsonTask(this.name, this.description, this.day, this.completed);
  }

  /**
   * Gets whether this task has been completed or not
   *
   * @return the boolean value of completion
   */
  public boolean getCompleted() {
    return this.completed;
  }

  /**
   * Determines if this task equals a given object.
   *
   * @param other the object to be compared
   * @return wheter or not the objects equal
   */
  public boolean equals(Object other) {
    if (!(other instanceof Task)) {
      return false;
    }

    Task that = (Task) other;
    return this.name.equals(that.name) && this.day.equals(that.day)
        && this.description.equals(that.description) && this.completed == that.completed;
  }

  /**
   * Updates the completed value of this task.
   *
   * @param selected the value of the completion status
   */
  public void changeCompleted(boolean selected) {
    this.completed = selected;
  }
}
