package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Event;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Handles interaction with the week view between the user and the model representation
 */
public class WeekViewController extends AbstractController {
  private File weekBujoFile;

  private Week week;

  @FXML
  private VBox sunTasksBox;
  @FXML
  private VBox sunEventsBox;
  @FXML
  private VBox monTasksBox;
  @FXML
  private VBox monEventsBox;
  @FXML
  private VBox tuesTasksBox;
  @FXML
  private VBox tuesEventsBox;
  @FXML
  private VBox wedTasksBox;
  @FXML
  private VBox wedEventsBox;
  @FXML
  private VBox thursTasksBox;
  @FXML
  private VBox thursEventsBox;
  @FXML
  private VBox friTasksBox;
  @FXML
  private VBox friEventsBox;
  @FXML
  private VBox satTasksBox;

  @FXML
  private VBox satEventsBox;

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

  @FXML
  private Button newTaskDone;

  @FXML
  private Button buttonDone;

  private Popup createNewTaskPopup = new Popup();
  private Popup createNewEventPopup = new Popup();

  public WeekViewController(Week week) {
    this.week = week;
  }

  /**
   * Runs the JavaFX scene based on a week's data of tasks and events
   */
  @Override
  public void run() {

    convertWeekTasksToGui();
    convertWeekEventsToGui();

    // saves file
    saveButton.setOnAction(e -> newFileCreation());

    // opens an existing file and updates week
    openExistingButton.setOnAction(e -> switchScene(this.menuBar,
        new OpenExistingFileController(), "openExistingFile.fxml"));

    // create new task
    this.newTask.setOnAction(e -> makeTaskPopup());
    FXMLLoader taskLoader = new FXMLLoader(getClass().getClassLoader().getResource("createNewTask.fxml"));
    taskLoader.setController(this);

    try {

      Scene s = taskLoader.load();
      this.createNewTaskPopup.getContent().add(s.getRoot());
      this.newTaskDone.setOnAction(e -> this.createNewTaskPopup.hide());

    } catch (IOException e) {
      throw new RuntimeException();
    }

    // create new event
    this.newEvent.setOnAction(e -> makeEventPopup());
    FXMLLoader eventLoader = new FXMLLoader(getClass().getClassLoader().getResource("createNewEvent.fxml"));
    eventLoader.setController(this);

    try {

      Scene s = eventLoader.load();
      this.createNewEventPopup.getContent().add(s.getRoot());
      this.buttonDone.setOnAction(e -> this.createNewEventPopup.hide());

    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  private void convertWeekTasksToGui() {

    for (Day d : this.week.getDays()) {
      for (Task t : d.getTasks()) {
        if (d.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
          HBox taskBox = createNewTask(t);
          sunTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
          HBox taskBox = createNewTask(t);
          monTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
          HBox taskBox = createNewTask(t);
          tuesTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
          HBox taskBox = createNewTask(t);
          wedTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
          HBox taskBox = createNewTask(t);
          thursTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
          HBox taskBox = createNewTask(t);
          friTasksBox.getChildren().add(taskBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
          HBox taskBox = createNewTask(t);
          satTasksBox.getChildren().add(taskBox);
        }
      }
    }
  }

  private HBox createNewTask(Task t) {

   // @FXML
    HBox taskBox = new HBox();
    CheckBox taskName = new CheckBox();
    Button viewButton = new Button();

    taskBox.getChildren().add(taskName);
    taskBox.getChildren().add(viewButton);

    //returning something random so the code can compile
    return taskBox;
  }

  private void convertWeekEventsToGui() {

    for (Day d : this.week.getDays()) {

      for (Event e : d.getEvents()) {
        if (d.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
          HBox eventBox = createNewEvent(e);
          sunEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
          HBox eventBox = createNewEvent(e);
          monEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
          HBox eventBox = createNewEvent(e);
          tuesEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
          HBox eventBox = createNewEvent(e);
          wedEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
          HBox eventBox = createNewEvent(e);
          thursEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
          HBox eventBox = createNewEvent(e);
          friEventsBox.getChildren().add(eventBox);
        } else if (d.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
          HBox eventBox = createNewEvent(e);
          satEventsBox.getChildren().add(eventBox);
        }
      }
    }
  }

  private HBox createNewEvent(Event e) {
    //returning something random so the code can compile
    return new HBox();
  }


  private void makeTaskPopup() {
    // this needs to be less ghetto
    createNewTaskPopup.show(this.menuBar.getScene().getWindow());
  }

  private void makeEventPopup() {
    // this needs to be less ghetto
    createNewEventPopup.show(this.menuBar.getScene().getWindow());
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
