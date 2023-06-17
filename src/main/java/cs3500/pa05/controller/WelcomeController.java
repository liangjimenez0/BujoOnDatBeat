package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.GraphicalView;
import cs3500.pa05.view.View;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Handles interaction between the user and the model in the welcome scene.
 */
public class WelcomeController extends AbstractController {

  private Week currentWeek;

  @FXML
  private Button createNewFile;

  @FXML
  private Button openExistingFile;
  /**
   * Initializes a welcome page scene on the Java Journal
   */
  @Override
  public void run() {
    createNewFile.setOnAction(e -> switchScene(createNewFile, new CreateNewFileController(),
        "createNewFile.fxml"));

    openExistingFile.setOnAction(e -> switchScene(openExistingFile, new OpenExistingFileController(),
        "openExistingFile.fxml"));
  }
}
