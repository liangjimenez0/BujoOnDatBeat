package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Handles the creation of a new task.
 */
public class CreateNewTaskController extends AbstractController {
  @FXML
  private TextField userTaskName, userTaskDay;

  @FXML
  private TextArea userTaskDescription;
  @FXML
  private Button newTaskDone, backToWeekView;

  private Week currentWeek;

  /**
   * Initializes an object that can create a new task within the given week.
   *
   * @param currentWeek the week to be updated with the new task created
   */
  public CreateNewTaskController(Week currentWeek) {
    this.currentWeek = currentWeek;
  }


  /**
   * Handles creating a new task when the user presses the done button.
   */
  public void run() {
    newTaskDone.setOnAction(e -> createNewTask());
    backToWeekView.setOnAction(e -> switchScene(newTaskDone,
        new WeekViewController(this.currentWeek), "weekView.fxml"));
  }

  /**
   * Creates a new task from the inputted information.
   */
  private void createNewTask() {

    String taskName = userTaskName.getText();
    DayOfWeek weekday = DayOfWeek.valueOf(userTaskDay.getText().toUpperCase());
    Task newTask;

    if (userTaskDescription.getText() != null) {
      for (Day day : this.currentWeek.getDays()) {
        if (day.getDayOfWeek() == weekday) {
          if (day.getTasks().size() + 1 > this.currentWeek.getMaxTasks()) {
            switchScene(newTaskDone, new WarningController(this.currentWeek), "warningScreen.fxml");
          } else {
            String taskDescription = userTaskDescription.getText();
            newTask = new Task(taskName, weekday, taskDescription);
            addTaskToWeek(newTask);
            switchScene(newTaskDone, new WeekViewController(this.currentWeek), "weekView.fxml");
          }
        }
      }
    }
  }

  /**
   * Adds a given task to the correct day in the week.
   *
   * @param newTask the task to be added to the week.
   */
  private void addTaskToWeek(Task newTask) {

    for (Day d : this.currentWeek.getDays()) {

      if (newTask.getDay().equals(d.getDayOfWeek())) {
        d.getTasks().add(newTask);
      }

    }
  }
}
