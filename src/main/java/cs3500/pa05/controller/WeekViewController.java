package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;

/**
 * Handles interaction with the week view between the user and the model representation
 */
public class WeekViewController extends AbstractController {
  private File weekBujoFile;

  private Week week;

  @FXML
  private VBox sunTasksBox, sunEventsBox, monTasksBox, monEventsBox, tuesTasksBox, tuesEventsBox,
      wedTasksBox, wedEventsBox, thursTasksBox, thursEventsBox, friTasksBox, friEventsBox,
      satTasksBox, satEventsBox, allTasksBox;
  @FXML
  private MenuBar menuBar;
  @FXML
  private MenuItem saveButton;
  @FXML
  private MenuItem openExistingButton;
  @FXML
  private MenuItem newTask;
  @FXML
  private MenuItem newEvent;
  @FXML
  private MenuItem newWeek;
  @FXML
  private MenuItem expandTaskQueueButton;
  private CreateNewTaskController taskController;
  @FXML
  private TextField userTaskName;

  public WeekViewController(Week week) {
    this.week = week;
  }

  /**
   * Runs the JavaFX scene based on a week's data of tasks and events
   */
  @Override
  public void run() {

    createTaskQueue();
    convertWeekTasksToGui();
    convertWeekEventsToGui();

    saveButton.setOnAction(e -> newFileCreation());

    openExistingButton.setOnAction(e -> switchScene(this.menuBar,
        new OpenExistingFileController(), "openExistingFile.fxml"));

    newTask.setOnAction(e ->
        switchScene(this.menuBar, new CreateNewTaskController(this.week), "createNewTask.fxml"));

    newEvent.setOnAction(
        e -> switchScene(this.menuBar, new CreateNewEventController(this.week),
            "createNewEvent.fxml"));

    newWeek.setOnAction(
        e -> switchScene(this.menuBar, new CreateNewFileController(), "createNewFile.fxml"));
  }


  private void createTaskQueue() {
    this.week.accumulateTasks();
    List<Task> allTasks = this.week.getAllTasks();

    allTasksBox.getChildren().clear();

    for (Task t : allTasks) {
      CheckBox taskCheckBox = convertTaskToGui(t);
      allTasksBox.getChildren().add(taskCheckBox);
    }
  }

  private CheckBox convertTaskToGui(Task t) {

    CheckBox checkBox = new CheckBox();
    String taskName = t.getName();
    Font font = Font.font("Avenir Book",
        FontWeight.NORMAL, 13);

    if (t.getCompleted()) {
      checkBox.setSelected(true);
    }

    checkBox.setText(taskName);
    checkBox.setFont(font);
    checkBox.setPrefWidth(234);
    checkBox.setMinHeight(35);
    checkBox.setMaxHeight(70);
    checkBox.wrapTextProperty().setValue(true);
    checkBox.setStyle("-fx-alignment: LEFT;");
    checkBox.setStyle("-fx-background-color: #F5F0BB; ");

    return checkBox;
  }

  private void convertWeekTasksToGui() {

    for (Day d : this.week.getDays()) {
      for (Task t : d.getTasks()) {
        if (d.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
          Button taskBox = createNewTask(t);
          sunTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
          Button taskBox = createNewTask(t);
          monTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
          Button taskBox = createNewTask(t);
          tuesTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
          Button taskBox = createNewTask(t);
          wedTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
          Button taskBox = createNewTask(t);
          thursTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
          Button taskBox = createNewTask(t);
          friTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
          Button taskBox = createNewTask(t);
          satTasksBox.getChildren().add(taskBox);
        }
      }
    }
  }

  private Button createNewTask(Task t) {
    Button taskButton = new Button();
    Font font = Font.font("Avenir Book",
        FontWeight.NORMAL, 13);

    taskButton.setText("- " + t.getName());
    taskButton.setFont(font);
    taskButton.setOnAction(
        e -> switchScene(taskButton, new ViewTaskController(t), "viewTask.fxml"));
    taskButton.wrapTextProperty().setValue(true);
    taskButton.setPrefWidth(169);
    taskButton.setMinHeight(26);
    taskButton.setMaxHeight(50);
    taskButton.setStyle("-fx-alignment: LEFT;");
    taskButton.setStyle("-fx-background-color: #DBDFAA; ");

    return taskButton;
  }

  private void convertWeekEventsToGui() {

    for (Day d : this.week.getDays()) {

      for (Event e : d.getEvents()) {
        if (d.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
          Button eventBox = createNewEvent(e);
          sunEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
          Button eventBox = createNewEvent(e);
          monEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
          Button eventBox = createNewEvent(e);
          tuesEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
          Button eventBox = createNewEvent(e);
          wedEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
          Button eventBox = createNewEvent(e);
          thursEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
          Button eventBox = createNewEvent(e);
          friEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
          Button eventBox = createNewEvent(e);
          satEventsBox.getChildren().add(eventBox);
        }
      }
    }
  }

  private Button createNewEvent(Event e) {
    Button eventButton = new Button();
    Font font = Font.font("Avenir Book",
        FontWeight.NORMAL, 13);

    eventButton.setText("- " + e.getName());
    eventButton.setFont(font);
    eventButton.setOnAction(
        event -> switchScene(eventButton, new ViewEventController(e), "eventTask.fxml"));
    eventButton.wrapTextProperty().setValue(true);
    eventButton.setPrefWidth(169);
    eventButton.setMinHeight(26);
    eventButton.setMaxHeight(50);
    eventButton.setStyle("-fx-alignment: LEFT;");
    eventButton.setStyle("-fx-background-color: #B3C890; ");

    return eventButton;
  }


  /**
   * Saves this Bujo file to the given file path that is entered from the user.
   */
  private void newFileCreation() {
    try {
      new CreateNewFile().createNewFile(week, weekBujoFile.toString());
    } catch (IOException e) {
      throw new NoSuchElementException("this bujo file cannot be saved with the given file path");
    }
  }
}
