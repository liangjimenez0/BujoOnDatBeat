package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Handles the display of a warning to the user
 */
public class WarningController extends AbstractController {
  @FXML
  private Button exitButton;
  @FXML
  private Button leaveErrorScreen;
  @FXML
  private Button invalidWidgetDone;
  @FXML
  private Button invalidPasswordDone;
  @FXML
  private HBox fileWarningHbox;
  private Week week;

  /**
   * Initializes an object that can display a warning to the user.
   *
   * @param week the week to check for different warnings needed to be displayed.
   */
  public WarningController(Week week) {
    this.week = week;
  }

  /**
   * Handles when to display warnings for certain buttons clicked.
   */
  @Override
  public void run() {
    if (this.leaveErrorScreen != null) {
      leaveErrorScreen.setOnAction(
          e -> switchScene(leaveErrorScreen, new WelcomeController(), "welcomePage.fxml"));
    } else if (this.exitButton != null) {
      exitButton.setOnAction(
          e -> switchScene(exitButton, new WeekViewController(this.week), "weekView.fxml"));
    } else if (this.invalidWidgetDone != null) {
      invalidWidgetDone.setOnAction(
          e -> switchScene(invalidWidgetDone, new WeekViewController(this.week), "weekView.fxml"));
    } else if (this.invalidPasswordDone != null) {
      invalidPasswordDone.setOnAction(
          e -> switchScene(invalidPasswordDone, new OpenExistingFileController(),
              "openExistingFile.fxml"));
    }
  }
}