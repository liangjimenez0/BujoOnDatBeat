package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WarningController extends AbstractController {

  @FXML
  Button exitButton;

  Week week;

  public WarningController(Week week) {
    this.week = week;
  }

  @Override
  public void run() {
    exitButton.setOnAction(
        e -> switchScene(exitButton, new WeekViewController(this.week), "weekView.fxml"));
  }
}
