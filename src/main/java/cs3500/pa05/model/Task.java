package cs3500.pa05.model;

import cs3500.pa05.json.JsonTask;

public class Task extends Widget {
  private boolean completed;

  public Task(String nameOfTask, DayOfWeek day, String description) {
    super(day, description, nameOfTask);
    this.completed = false;
  }

  public Task(String nameOfTask, DayOfWeek day, String description, boolean completed) {
    super(day, description, nameOfTask);
    this.completed = completed;
  }

  public JsonTask taskToJson() {
    return new JsonTask(this.nameOfTask, this.description, this.day, this.completed);
  }
}
