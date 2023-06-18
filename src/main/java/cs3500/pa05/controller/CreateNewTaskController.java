package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

public class CreateNewTaskController extends AbstractController {
  @FXML
  private TextField userTaskName, userTaskDay;

  @FXML
  private TextArea userTaskDescription;
  @FXML
  private Button newTaskDone;

  private Week currentWeek;

  public CreateNewTaskController(Week currentWeek) {
    this.currentWeek = currentWeek;
  }


  public void run() {
    newTaskDone.setOnAction(e -> createNewTask());
  }

  private void createNewTask() {
    String taskName = userTaskName.getText();
    DayOfWeek weekday = DayOfWeek.valueOf(userTaskDay.getText().toUpperCase());
    Task newTask;

    if (userTaskDescription.getText() != null) {
      String taskDescription = userTaskDescription.getText();
      newTask = new Task(taskName, weekday, taskDescription);
    } else {
      newTask = new Task(taskName, weekday);
    }

    addTaskToWeek(newTask);

    switchScene(newTaskDone, new WeekViewController(this.currentWeek), "weekView.fxml");
  }

  private void addTaskToWeek(Task newTask) {

    for (Day d : this.currentWeek.getDays()) {

      if (newTask.getDay().equals(d.getDayOfWeek())) {
        d.getTasks().add(newTask);
      }

    }

  }
}
