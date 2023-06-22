package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.DayOfWeek;
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
  @FXML
  private TextField startWeekday;
  @FXML
  private TextField passwordInput;
  @FXML
  private TextField newWeekNameInput;
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
    this.backButton.getScene().getWindow().centerOnScreen();
    backButton.setOnAction(
        e -> switchScene(backButton, new WelcomeController(), "welcomePage.fxml"));
    submitButton.setOnAction(e -> createNewFile());
  }

  /**
   * Creates a new file from the user-entered information.
   */
  private void createNewFile() {
    try {
      int maxTasks = Integer.parseInt(maxTasksInput.getText());
      int maxEvents = Integer.parseInt(maxEventsInput.getText());
      String fileName = fileNameInput.getText();
      String weekdayStart = startWeekday.getText();
      String password = passwordInput.getText();
      String weekName = newWeekNameInput.getText();
      if (!fileName.endsWith("bujo") || weekdayStart.isEmpty() || password.isEmpty()
          || maxTasks < 0 || maxEvents < 0 || !isDayOfWeek(weekdayStart)) {
        switchScene(submitButton, new WarningController(this.currentWeek),
            "invalidFileWarning.fxml");
      } else {
        this.currentWeek =
            new Week(maxTasks, maxEvents, fileName, weekdayStart.toUpperCase(), password, weekName);
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


  /**
   * Determines if the given string corresponds to a day of the week.
   *
   * @param s the string being compared
   * @return if the string is a day of the week
   */
  private boolean isDayOfWeek(String s) {
    return DayOfWeek.MONDAY.name().equals(s.toUpperCase())
        || DayOfWeek.TUESDAY.name().equals(s.toUpperCase())
        || DayOfWeek.WEDNESDAY.name().equals(s.toUpperCase())
        || DayOfWeek.THURSDAY.name().equals(s.toUpperCase())
        || DayOfWeek.FRIDAY.name().equals(s.toUpperCase())
        || DayOfWeek.SATURDAY.name().equals(s.toUpperCase())
        || DayOfWeek.SUNDAY.name().equals(s.toUpperCase());
  }
}
