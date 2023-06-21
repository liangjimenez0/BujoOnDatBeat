package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Represents the
 */
public class ChangeWeekNameController extends AbstractController {

  @FXML
  private TextField newWeekName;

  @FXML
  private Button newWeekNameDone, backButton;

  private Week week;

  public ChangeWeekNameController(Week week) {
    this.week = week;
  }



  @Override
  public void run() {
    this.backButton.getScene().getWindow().centerOnScreen();
    backButton.setOnAction(
        e -> switchScene(newWeekNameDone, new WeekViewController(this.week), "weekView.fxml"));
    newWeekNameDone.setOnAction(
        e -> setNewTitle());
  }



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
