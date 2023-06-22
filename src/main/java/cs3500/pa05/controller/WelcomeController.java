package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Handles interaction between the user and the model in the welcome scene.
 */
public class WelcomeController extends AbstractController {

  private Week currentWeek;
  @FXML
  private Button createNewFile;
  @FXML
  private Button openExistingFile;
  @FXML
  private Button openFileAsTemplate;

  /**
   * Initializes a welcome page scene on the Java Journal
   */
  @Override
  public void run() {
    createNewFile.getScene().getWindow().centerOnScreen();
    createNewFile.setOnAction(e -> switchScene(createNewFile, new CreateNewFileController(),
        "createNewFile.fxml"));

    openExistingFile.setOnAction(
        e -> switchScene(openExistingFile, new OpenExistingFileController(),
            "openExistingFile.fxml"));

    openFileAsTemplate.setOnAction(
        e -> switchScene(
            openFileAsTemplate, new OpenFileAsTemplateController(), "openFileAsTemplate.fxml"));
  }
}
