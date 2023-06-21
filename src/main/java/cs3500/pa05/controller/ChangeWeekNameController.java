package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Represents an object that can change the week name of a bujo.
 */
public class ChangeWeekNameController extends AbstractController {

  @FXML
  private TextField newWeekName;

  @FXML
  private Button newWeekNameDone;
  @FXML
  private Button backButton;

  private Week week;

  /**
   * Initialize an object that can change the week name.
   *
   * @param week the week to change names
   */
  public ChangeWeekNameController(Week week) {
    this.week = week;
  }


  /**
   * Initializes controls for each button on this scene.
   */
  @Override
  public void run() {
    this.backButton.getScene().getWindow().centerOnScreen();
    backButton.setOnAction(
        e -> switchScene(newWeekNameDone, new WeekViewController(this.week), "weekView.fxml"));
    newWeekNameDone.setOnAction(
        e -> setNewTitle());
  }


  /**
   * Updates the week to contain the new tite.
   */
  private void setNewTitle() {
    if (newWeekName.getText() != null) {
      this.week.setNameForWeek(newWeekName.getText());
      switchScene(newWeekNameDone,
          new WeekViewController(this.week), "weekView.fxml");
    } else {
      switchScene(newWeekNameDone, new WarningController(this.week), "warningScreen.fxml");
    }
  }
}
