package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WarningController extends AbstractController {

  @FXML
  Button exitButton;

  Week week;

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
    exitButton.setOnAction(
        e -> switchScene(exitButton, new WeekViewController(this.week), "weekView.fxml"));
  }
}
