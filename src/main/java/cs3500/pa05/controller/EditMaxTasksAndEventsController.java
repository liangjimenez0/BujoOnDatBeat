package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Handles the editing of max tasks and events.
 */
public class EditMaxTasksAndEventsController extends AbstractController {

  @FXML
  private TextField maxTasksInput;
  @FXML
  private TextField maxEventsInput;
  @FXML
  private Button doneButton;
  @FXML
  private Button backButton;
  private Week week;

  /**
   * Initializes an object that can edit a week's max tasks and events.
   *
   * @param week the week to be edited.
   */
  public EditMaxTasksAndEventsController(Week week) {
    this.week = week;
  }

  /**
   * Handles the different actions a user can take while editing a max task/event
   */
  public void run() {
    this.doneButton.getScene().getWindow().centerOnScreen();
    doneButton.setOnAction(e -> editMax());
    backButton.setOnAction(e ->
        switchScene(backButton, new WeekViewController(this.week), "weekView.fxml"));
  }

  /**
   * Updates the week and returns to the week view.
   */
  private void editMax() {
    this.week.changeMaxTasks(Integer.parseInt(maxTasksInput.getText()));
    this.week.changeMaxEvents(Integer.parseInt(maxEventsInput.getText()));
    switchScene(backButton, new WeekViewController(this.week), "weekView.fxml");
  }
}
