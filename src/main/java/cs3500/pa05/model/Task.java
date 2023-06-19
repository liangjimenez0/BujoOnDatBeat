package cs3500.pa05.model;

import cs3500.pa05.json.JsonTask;

/**
 * Represents a task that occurs on a day with a name and description of the task
 */
public class Task extends Widget {
  private boolean completed;

  /**
   * @param name        is the name of this task
   * @param day         is the day this task occurs on
   * @param description is a short description of what this task is
   */
  public Task(String name, DayOfWeek day, String description) {
    super(day, name, description);
    this.completed = false;
  }

  /**
   * @param name is the name of this task
   * @param day        is the day this task occurs on
   */
  public Task(String name, DayOfWeek day) {
    super(day, name);
    this.completed = false;
  }

  /**
   * @param name  is the name of this task
   * @param day         is the day this task occurs on
   * @param description is a short description of what this task is
   * @param completed   is a boolean value of whether this task is completed or not
   */
  public Task(String name, DayOfWeek day, String description, boolean completed) {
    super(day, name, description);
    this.completed = completed;
  }

  /**
   * @return a JsonTask that is this task turned to JsonTask record
   */
  public JsonTask taskToJson() {
    return new JsonTask(this.name, this.description, this.day, this.completed);
  }

  public boolean getCompleted() {
    return this.completed;
  }

  public boolean equals(Object other) {
    if (!(other instanceof Task)) {
      return false;
    }

    Task that = (Task) other;
    return this.name.equals(that.name) && this.day.equals(that.day) &&
        this.description.equals(that.description) && this.completed == that.completed;
  }

  public void changeCompleted(boolean selected) {
    this.completed = selected;
  }
}
