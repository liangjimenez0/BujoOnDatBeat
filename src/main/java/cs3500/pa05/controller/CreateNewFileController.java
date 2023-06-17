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
  private TextField maxTasksInput = new TextField();

  @FXML
  private TextField maxEventsInput = new TextField();

  @FXML
  private TextField fileNameInput = new TextField();

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
    int maxTasks = Integer.parseInt(maxTasksInput.getText());
    int maxEvents = Integer.parseInt(maxEventsInput.getText());
    String fileName = fileNameInput.getText();

    this.currentWeek = new Week(maxTasks, maxEvents);

    try {
      new CreateNewFile().createNewFile(this.currentWeek, fileName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    switchScene(submitButton, new WeekViewController(this.currentWeek), "weekView.fxml");
  }
}
