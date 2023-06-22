package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Handles the expanding of an event in the view.
 */
public class ViewTaskController extends AbstractController {
  @FXML
  private TextField userTaskName;
  @FXML
  private TextField userTaskDay;
  @FXML
  private TextArea userTaskDescription;
  @FXML
  private Button deleteTask;
  @FXML
  private Button doneViewing;
  @FXML
  private CheckBox completedBox;
  private Task currentTask;
  private Week week;

  /**
   * Initializes an object that can view and edit a task in a given week.
   *
   * @param task the task being expanded
   * @param week the current week
   */
  public ViewTaskController(Task task, Week week) {
    this.currentTask = task;
    this.week = week;
  }

  /**
   * Handles different button presses.
   */
  public void run() {
    this.deleteTask.getScene().getWindow().centerOnScreen();
    setTextFields();

    deleteTask.setOnAction(e -> deleteTask());
    doneViewing.setOnAction(
        e -> editTask());
  }

  /**
   * Shows the user the current information in the task.
   */
  private void setTextFields() {
    userTaskDay.setText(String.valueOf(currentTask.getDay()));
    userTaskName.setText(currentTask.getName());
    completedBox.setSelected(currentTask.getCompleted());

    if (currentTask.getDescription() != null) {
      userTaskDescription.setText(currentTask.getDescription());
    }
  }

  /**
   * Updates a task to contain the new desired information.
   */
  private void editTask() {
    for (Day d : this.week.getDays()) {
      for (Task t : d.getTasks()) {

        if (t.equals(this.currentTask)) {
          t.changeName(userTaskName.getText());
          t.changeDay(DayOfWeek.valueOf(userTaskDay.getText().toUpperCase()));
          t.changeDescription(userTaskDescription.getText());
          t.changeCompleted(completedBox.isSelected());

          // if the day has been changed, remove the event from its current day
          // and add to the correct day.
          if (t.getDay() != d.getDayOfWeek()) {
            d.getTasks().remove(t);
            for (Day newDay : this.week.getDays()) {
              if (t.getDay() == newDay.getDayOfWeek()) {
                newDay.getTasks().add(t);
              }
            }
          }
        }
      }
    }
    switchScene(doneViewing, new WeekViewController(this.week), "weekView.fxml");
  }

  /**
   * Deletes the task in this controller.
   */
  private void deleteTask() {
    for (Day d : this.week.getDays()) {
      for (Task t : d.getTasks()) {

        if (t.equals(this.currentTask)) {
          d.getTasks().remove(t);
          break;
        }
      }
    }
    switchScene(deleteTask, new WeekViewController(this.week), "weekView.fxml");
  }
}
