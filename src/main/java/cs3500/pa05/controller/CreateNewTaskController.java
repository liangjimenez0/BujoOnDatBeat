package cs3500.pa05.controller;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

public class CreateNewTaskController extends AbstractController{

  public Task newlyCreatedTask;
  @FXML
  private TextField userTaskName, userTaskDay, userDescription;

  @FXML
  private Button newTaskDone;

  private Popup createNewTaskPopup = new Popup();

  private Control c;

  public CreateNewTaskController(Control c) {
    this.c = c;
  }


  public void run() {
    String taskName = userTaskName.getText();
    DayOfWeek weekday = DayOfWeek.valueOf(userTaskDay.getText().toUpperCase());
    String taskDescription = userDescription.getText();

    this.newlyCreatedTask = new Task(taskName, weekday, taskDescription);
  }


  public Task getNewlyCreatedTask() {
    return this.newlyCreatedTask;
  }


  private void taskCreation() {
    FXMLLoader taskLoader =
        new FXMLLoader(getClass().getClassLoader().getResource("createNewTask.fxml"));
    taskLoader.setController(this);

    try {
      Scene s = taskLoader.load();
      this.createNewTaskPopup.getContent().add(s.getRoot());
      this.newTaskDone.setOnAction(e -> {

        run();
        this.createNewTaskPopup.hide();
      });
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }


  private void makeTaskPopup() {
    createNewTaskPopup.show(this.c.getScene().getWindow());
  }
}
