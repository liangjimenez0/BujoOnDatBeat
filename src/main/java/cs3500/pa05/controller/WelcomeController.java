package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.GraphicalView;
import cs3500.pa05.view.View;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WelcomeController extends AbstractController {

  Week currentWeek;
  @FXML
  private Button createNewFile, openExistingFile;


  @Override
  public void run() {
    createNewFile.setOnAction(e -> switchScene(new CreateNewFileController(),
        "src/main/resources/welcomePage.fxml"));
    openExistingFile.setOnAction(e -> switchScene(new WeekViewController(), "weekView.fxml"));
  }

  private void processCreateNewFile() throws IOException {
//    switchScene("createNewFile.fxml");
//
//    int maxTasks = Integer.parseInt(maxTasksInput.getText());
//    int maxEvents = Integer.parseInt(maxEventsInput.getText());
//    String fileName = fileNameInput.getText();
//
//    this.currentWeek = new Week(maxTasks, maxEvents);
//    new CreateNewFile().createNewFile(this.currentWeek, fileName);
//    weekToGraphicalView();
  }

  private void weekToGraphicalView() {

  }

}
