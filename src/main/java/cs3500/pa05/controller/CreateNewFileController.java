package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.Week;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * The controller that handles new file creation
 */
public class CreateNewFileController extends AbstractController {
  @FXML
  private TextField maxTasksInput;

  @FXML
  private TextField maxEventsInput;

  @FXML
  private TextField fileNameInput;

  private Week currentWeek;

  @FXML
  private Button submitButton;

  @FXML
  private Button backButton;

  /**
   * Runs the creation of a file with a week's information as a JavaFX scene
   */
  @Override
  public void run() {
    backButton.setOnAction(e -> switchScene(backButton, new WelcomeController(), "welcomePage.fxml"));

    submitButton.setOnAction(e -> createNewFile());
  }

  private void createNewFile() {
    int maxTasks;
    int maxEvents;
    try {
      maxTasks = Integer.parseInt(maxTasksInput.getText());
      maxEvents = Integer.parseInt(maxEventsInput.getText());
      String fileName = fileNameInput.getText();
      if (!fileName.endsWith("bujo")) {
        switchScene(submitButton, new WarningController(this.currentWeek),
            "invalidFileWarning.fxml");
      } else if (maxTasks < 0 || maxEvents < 0) {
        switchScene(submitButton, new WarningController(this.currentWeek),
            "invalidFileWarning.fxml");
      } else {
        maxTasks = Integer.parseInt(maxTasksInput.getText());
        maxEvents = Integer.parseInt(maxEventsInput.getText());
        this.currentWeek = new Week(maxTasks, maxEvents, fileName);
        try {
          new CreateNewFile().createNewFile(this.currentWeek, fileName);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        switchScene(submitButton, new WeekViewController(this.currentWeek), "weekView.fxml");
      }
    } catch (NumberFormatException e) {
      switchScene(submitButton, new WarningController(this.currentWeek),
          "invalidFileWarning.fxml");
    }
  }
}
