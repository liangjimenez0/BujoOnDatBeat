package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.Week;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateNewFileController extends AbstractController{

  @FXML
  private TextField maxTasksInput, maxEventsInput, fileNameInput;
  private Week currentWeek;
  @FXML
  private Button submit;

  @Override
  public void run() {
    int maxTasks = Integer.parseInt(maxTasksInput.getText());
    int maxEvents = Integer.parseInt(maxEventsInput.getText());
    String fileName = fileNameInput.getText();

    this.currentWeek = new Week(maxTasks, maxEvents);

    try {
      new CreateNewFile().createNewFile(this.currentWeek, fileName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    submit.setOnAction(e -> switchScene(new WeekViewController(), "weekView.fxml"));
  }

}
