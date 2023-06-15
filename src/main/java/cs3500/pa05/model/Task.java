package cs3500.pa05.model;

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

  public StringBuilder taskInStringForm() {
    StringBuilder builder = new StringBuilder();

    builder.append("Name: ").append(this.nameOfTask).append(", ");
    builder.append("Description: ").append(this.description).append(", ");
    builder.append("Day of week: ").append(this.day).append(", ");
    builder.append("Completed?: ").append(this.completed).append(", ");

    return builder;
  }

}
