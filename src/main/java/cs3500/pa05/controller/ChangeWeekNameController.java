package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChangeWeekNameController extends AbstractController {

  @FXML
  private TextField newWeekName;


  @FXML
  private Button newWeekNameDone, backButton;

  private Week week;

  private String bujoFileName;

  private Label given;


  public ChangeWeekNameController(Week week) {
    this.week = week;


  }
  @Override
  public void run() {
    backButton.setOnAction(
        e -> switchScene(newWeekNameDone, new WeekViewController(this.week), "weekView.fxml"));

    newWeekNameDone.setOnAction(
        e -> setNewTitle());

  }


  private void setNewTitle() {
    if (newWeekName.getText() != null) {
     // this.given.setText(newWeekName.getText());
      switchScene(newWeekNameDone,
          new WeekViewController(this.week, newWeekName.getText()), "weekView.fxml");
    } else {
      switchScene(newWeekNameDone, new WarningController(this.week), "warningScreen.fxml");
    }
  }
}
