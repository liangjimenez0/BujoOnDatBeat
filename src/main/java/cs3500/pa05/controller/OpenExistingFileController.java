package cs3500.pa05.controller;

import cs3500.pa05.model.ReadFile;
import cs3500.pa05.model.Week;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Handles opening of a given file to the view.
 */
public class OpenExistingFileController extends AbstractController{
  @FXML
  private TextField fileNameInput;
  @FXML
  private Button submitButton;
  @FXML
  private Button backButton;

  private Week currentWeek;

  /**
   * Handles what happens when the buttons on this scene are clicked.
   */
  public void run(){
    backButton.setOnAction(e -> switchScene(backButton, new WelcomeController(), "welcomePage.fxml"));

    submitButton.setOnAction(e -> processFile());
  }

  /**
   * Takes the inputted information and loads the given file into the view.
   */
  private void processFile() {
    String fileName = fileNameInput.getText();
    File file = new File(fileName);

    this.currentWeek = new ReadFile(file).processFile();

    switchScene(submitButton, new WeekViewController(this.currentWeek), "weekView.fxml");
  }
}
