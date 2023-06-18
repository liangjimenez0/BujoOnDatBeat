package cs3500.pa05.model;

import cs3500.pa05.json.JsonTask;

/**
 * Represents a task that occurs on a day with a name and description of the task
 */
public class Task extends Widget {
  private boolean completed;

  /**
   * @param nameOfTask is the name of this task
   * @param day is the day this task occurs on
   * @param description is a short description of what this task is
   */
  public Task(String nameOfTask, DayOfWeek day, String description) {
    super(day, description, nameOfTask);
    this.completed = false;
  }

  /**
   * @param nameOfTask is the name of this task
   * @param day is the day this task occurs on
   */
  public Task(String nameOfTask, DayOfWeek day) {
    super(day, nameOfTask);
    this.completed = false;
  }

  /**
   * @param nameOfTask is the name of this task
   * @param day is the day this task occurs on
   * @param description is a short description of what this task is
   * @param completed is a boolean value of whether this task is completed or not
   */
  public Task(String nameOfTask, DayOfWeek day, String description, boolean completed) {
    super(day, description, nameOfTask);
    this.completed = completed;
  }

  /**
   * @return a JsonTask that is this task turned to JsonTask record
   */
  public JsonTask taskToJson() {
    return new JsonTask(this.nameOfTask, this.description, this.day, this.completed);
  }

  public boolean getCompleted() {
    return this.completed;
  }
}
