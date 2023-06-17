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

/**
 * Handles interaction between the user and the model in the welcome scene.
 */
public class WelcomeController extends AbstractController {

  private Week currentWeek;
  @FXML
  private Button createNewFile = new Button();
  private Button openExistingFile = new Button();

  /**
   * Initializes a welcome page scene on the Java Journal
   */
  @Override
  public void run() {
    createNewFile.setOnAction(e -> switchScene(new CreateNewFileController(),
        "src/main/resources/welcomePage.fxml"));
    openExistingFile.setOnAction(e -> switchScene(new WeekViewController(), "weekView.fxml"));
  }

  /**
   * This handles the user button press to "create a new file".
   *
   * @throws IOException when this method is interrupted
   */
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

  /**
   * Uses the data from a Week object and creates a JavaFX scene from the data
   */
  private void weekToGraphicalView() {

  }
}
