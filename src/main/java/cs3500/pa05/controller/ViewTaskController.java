package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ViewTaskController extends AbstractController{

  @FXML
  private TextField userTaskName, userTaskDay;

  @FXML
  private TextArea userTaskDescription;
  @FXML
  Button deleteTask, doneViewing;
  @FXML
  CheckBox completedBox;
  Task currentTask;
  Week week;


  public ViewTaskController(Task task, Week week) {
    this.currentTask = task;
    this.week = week;
  }

  public void run() {

    setTextFields();

    deleteTask.setOnAction(e -> deleteTask());
    doneViewing.setOnAction(
        e -> editTask());

  }

  private void setTextFields() {

    userTaskDay.setText(String.valueOf(currentTask.getDay()));
    userTaskName.setText(currentTask.getName());
    completedBox.setSelected(currentTask.getCompleted());

    if (currentTask.getDescription() != null) {
      userTaskDescription.setText(currentTask.getDescription());
    }
  }

  private void editTask() {

    for (Day d : this.week.getDays()) {

      for (Task t : d.getTasks()) {

        if (t.equals(this.currentTask)) {
          t.changeName(userTaskName.getText());
          t.changeDay(DayOfWeek.valueOf(userTaskDay.getText().toUpperCase()));
          t.changeDescription(userTaskDescription.getText());
          t.changeCompleted(completedBox.isSelected());
        }
      }
    }

    switchScene(doneViewing, new WeekViewController(this.week), "weekView.fxml");
  }

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
