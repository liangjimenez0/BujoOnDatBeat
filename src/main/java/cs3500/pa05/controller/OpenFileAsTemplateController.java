package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.ReadFile;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class OpenFileAsTemplateController extends AbstractController {

  @FXML
  private TextField fileNameInput, newFileNameInput, newWeekNameInput;
  @FXML
  private Button submitButton;
  @FXML
  private Button backButton;

  private Week currentWeek;

  public void run() {
    this.backButton.getScene().getWindow().centerOnScreen();

    backButton.setOnAction(
        e -> switchScene(backButton, new WelcomeController(), "welcomePage.fxml"));

    submitButton.setOnAction(e -> processFile());
  }

  private void processFile() {
    String fileName = fileNameInput.getText();
    File file = new File(fileName);

    String newFileName = newFileNameInput.getText();
    String newWeekName = newWeekNameInput.getText();


    if (!fileName.endsWith("bujo") || new ReadFile(file).processFile() == null) {
      switchScene(submitButton, new WarningController(this.currentWeek),
          "invalidFileWarning.fxml");
    } else {
      Week weekTemplate = new ReadFile(file).processFile();

      this.currentWeek =
          new Week(weekTemplate.getMaxTasks(), weekTemplate.getMaxEvents(), newFileName,
              String.valueOf(weekTemplate.getWeekdayStart()), weekTemplate.getPassword(),
              newWeekName);

      try {
        new CreateNewFile().createNewFile(this.currentWeek, newFileName);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      switchScene(submitButton, new WeekViewController(this.currentWeek), "weekView.fxml");
    }
  }
}
