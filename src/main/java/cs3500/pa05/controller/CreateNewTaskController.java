package cs3500.pa05.controller;

import cs3500.pa05.model.DayOfWeek;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CreateNewTaskController extends AbstractController{

  @FXML
  private TextField userTaskName, userTaskDay, userDescription;


  public void run() {
    String taskName = userTaskName.getText();
    DayOfWeek weekday = DayOfWeek.valueOf(userTaskDay.getText().toUpperCase());
    String taskDescription = userDescription.getText();
  }
}
