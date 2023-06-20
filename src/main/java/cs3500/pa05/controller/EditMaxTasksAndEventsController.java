package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditMaxTasksAndEventsController extends AbstractController {

  @FXML
  private TextField maxTasksInput, maxEventsInput;

  @FXML
  private Button doneButton, backButton;

  private Week week;

  public EditMaxTasksAndEventsController(Week week) {
    this.week = week;
  }

  public void run() {
    this.doneButton.getScene().getWindow().centerOnScreen();
    doneButton.setOnAction(e -> editMax());
    backButton.setOnAction(e -> switchScene(backButton, new WeekViewController(this.week), "weekView.fxml"));
  }

  private void editMax() {
    this.week.changeMaxTasks(Integer.parseInt(maxTasksInput.getText()));
    this.week.changeMaxEvents(Integer.parseInt(maxEventsInput.getText()));

    switchScene(backButton, new WeekViewController(this.week), "weekView.fxml");
  }
}
