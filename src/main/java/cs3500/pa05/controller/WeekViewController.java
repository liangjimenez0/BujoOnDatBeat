package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * Handles interaction with the week view between the user and the model representation
 */
public class WeekViewController extends AbstractController {
  private final Week week;
  @FXML
  private Label bujoTitle;
  @FXML
  private Label dayOneLabel;
  @FXML
  private Label dayTwoLabel;
  @FXML
  private Label dayThreeLabel;
  @FXML
  private Label dayFourLabel;
  @FXML
  private Label dayFiveLabel;
  @FXML
  private Label daySixLabel;
  @FXML
  private Label daySevenLabel;
  @FXML
  private Button newTaskButton;
  @FXML
  private Button newEventButton;
  @FXML
  private VBox dayOneTasksBox;
  @FXML
  private VBox dayOneEventsBox;
  @FXML
  private VBox dayTwoTasksBox;
  @FXML
  private VBox dayTwoEventsBox;
  @FXML
  private VBox dayThreeTasksBox;
  @FXML
  private VBox dayThreeEventsBox;
  @FXML
  private VBox dayFourTasksBox;
  @FXML
  private VBox dayFourEventsBox;
  @FXML
  private VBox dayFiveTasksBox;
  @FXML
  private VBox dayFiveEventsBox;
  @FXML
  private VBox daySixTasksBox;
  @FXML
  private VBox daySixEventsBox;
  @FXML
  private VBox daySevenTasksBox;
  @FXML
  private VBox daySevenEventsBox;
  @FXML
  private VBox allTasksBox;
  @FXML
  private VBox dayOneBox;
  @FXML
  private VBox dayTwoBox;
  @FXML
  private VBox dayThreeBox;
  @FXML
  private VBox dayFourBox;
  @FXML
  private VBox dayFiveBox;
  @FXML
  private VBox daySixBox;
  @FXML
  private VBox daySevenBox;
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
  private MenuItem renameWeekButton;
  @FXML
  private MenuItem editMaxTasksAndEventsButton;
  @FXML
  private MenuItem openFileAsTemplateButton;
  private CreateNewTaskController taskController;
  @FXML
  private TextField userTaskName;
  @FXML
  private ProgressBar dayOneProgressBar;
  @FXML
  private ProgressBar dayTwoProgressBar;
  @FXML
  private ProgressBar dayThreeProgressBar;
  @FXML
  private ProgressBar dayFourProgressBar;
  @FXML
  private ProgressBar dayFiveProgressBar;
  @FXML
  private ProgressBar daySixProgressBar;
  @FXML
  private ProgressBar daySevenProgressBar;
  @FXML
  private Label dayOneProgLabel;
  @FXML
  private Label dayTwoProgLabel;
  @FXML
  private Label dayThreeProgLabel;
  @FXML
  private Label dayFourProgLabel;
  @FXML
  private Label dayFiveProgLabel;
  @FXML
  private Label daySixProgLabel;
  @FXML
  private Label daySevenProgLabel;
  private List<CheckBox> allCheckBoxes = new ArrayList<>();
  private String newName;

  /**
   * Initializes an object that displays a given week.
   *
   * @param week the week to be viewed
   */
  public WeekViewController(Week week) {
    this.week = week;
  }

  /**
   * Initializes an object that displays a given week with a custom name
   *
   * @param week the week to be viewed
   * @param s    the custom week name
   */
  public WeekViewController(Week week, String s) {
    this.week = week;
    this.newName = s;
  }

  /**
   * Runs the JavaFX scene based on a week's data of tasks and events
   */
  @Override
  public void run() {
    this.menuBar.getScene().getWindow().centerOnScreen();
    this.bujoTitle.setText(week.getNameForWeek());
    setWeekdays();
    settingShortcuts();
    createTaskQueue();
    convertWeekTasksToGui();
    convertWeekEventsToGui();
    updateProgressBars();
    saveButton.setOnAction(e -> newFileCreation());
    openExistingButton.setOnAction(e -> {
      switchScene(this.menuBar,
          new OpenExistingFileController(), "openExistingFile.fxml");
      newFileCreation();
    });
    newTask.setOnAction(e -> newTask());
    newTaskButton.setOnAction(e -> newTask());
    newEvent.setOnAction(e -> newEvent());
    newEventButton.setOnAction(e -> newEvent());
    newWeek.setOnAction(
        e -> {
          switchScene(this.menuBar, new CreateNewFileController(), "createNewFile.fxml");
          newFileCreation();
        });
    renameWeekButton.setOnAction(e -> {
      switchScene(this.menuBar,
          new ChangeWeekNameController(this.week), "enterWeekName.fxml");
      mutateJournalName();
    });
    editMaxTasksAndEventsButton.setOnAction(e -> switchScene(this.menuBar,
        new EditMaxTasksAndEventsController(this.week), "editMaxTasksAndEvents.fxml"));
    openFileAsTemplateButton.setOnAction(
        e -> {
          switchScene(this.menuBar, new OpenFileAsTemplateController(),
              "openFileAsTemplate.fxml");
          newFileCreation();
        });
  }

  /**
   * Sets all the days of the week.
   */
  private void setWeekdays() {
    this.dayOneLabel.setText(this.week.getDays().get(0).getDayOfWeek().name().toLowerCase());
    this.dayTwoLabel.setText(this.week.getDays().get(1).getDayOfWeek().name().toLowerCase());
    this.dayThreeLabel.setText(this.week.getDays().get(2).getDayOfWeek().name().toLowerCase());
    this.dayFourLabel.setText(this.week.getDays().get(3).getDayOfWeek().name().toLowerCase());
    this.dayFiveLabel.setText(this.week.getDays().get(4).getDayOfWeek().name().toLowerCase());
    this.daySixLabel.setText(this.week.getDays().get(5).getDayOfWeek().name().toLowerCase());
    this.daySevenLabel.setText(this.week.getDays().get(6).getDayOfWeek().name().toLowerCase());

    this.dayOneBox.setId(this.week.getDays().get(0).getDayOfWeek().name().toLowerCase());
    this.dayTwoBox.setId(this.week.getDays().get(1).getDayOfWeek().name().toLowerCase());
    this.dayThreeBox.setId(this.week.getDays().get(2).getDayOfWeek().name().toLowerCase());
    this.dayFourBox.setId(this.week.getDays().get(3).getDayOfWeek().name().toLowerCase());
    this.dayFiveBox.setId(this.week.getDays().get(4).getDayOfWeek().name().toLowerCase());
    this.daySixBox.setId(this.week.getDays().get(5).getDayOfWeek().name().toLowerCase());
    this.daySevenBox.setId(this.week.getDays().get(6).getDayOfWeek().name().toLowerCase());
  }

  /**
   * Updates the current name of the week.
   */
  private void mutateJournalName() {
    this.week.setNameForWeek(newName);
  }

  /**
   * Displays the new task popup to the user.
   */
  private void newTask() {
    switchScene(this.menuBar, new CreateNewTaskController(this.week), "createNewTask.fxml");
  }

  /**
   * Displays the new event popup to the user.
   */
  private void newEvent() {
    switchScene(this.menuBar, new CreateNewEventController(this.week), "createNewEvent.fxml");
  }

  /**
   * Populates the task queue with all the week tasks.
   */
  private void createTaskQueue() {
    List<Task> allTasks = this.week.getAllTasks();

    allTasksBox.getChildren().clear();

    for (Task t : allTasks) {
      CheckBox taskCheckBox = convertTaskToGui(t);
      allTasksBox.getChildren().add(taskCheckBox);
      allCheckBoxes.add(taskCheckBox);
    }
  }


  /**
   * Determines if a task should be displayed as completed.
   *
   * @param t the task
   * @return whether the task is completed or not
   */
  private CheckBox convertTaskToGui(Task t) {
    CheckBox checkBox = new CheckBox();
    checkBox.setDisable(true);
    checkBox.setOpacity(1);
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
    checkBox.setTextAlignment(TextAlignment.LEFT);
    checkBox.setStyle("-fx-background-color: #F5F0BB; ");
    checkBox.setStyle("-fx-padding: 0 0 0 10;");
    return checkBox;
  }


  /**
   * Converts the week tasks to be displayable.
   */
  private void convertWeekTasksToGui() {
    for (Day d : this.week.getDays()) {
      for (Task t : d.getTasks()) {
        if (String.valueOf(t.getDay()).toLowerCase().equals(dayOneBox.getId())) {
          Button taskBox = createNewTask(t);
          dayOneTasksBox.getChildren().add(taskBox);
        } else if (String.valueOf(t.getDay()).toLowerCase().equals(dayTwoBox.getId())) {
          Button taskBox = createNewTask(t);
          dayTwoTasksBox.getChildren().add(taskBox);
        } else if (String.valueOf(t.getDay()).toLowerCase().equals(dayThreeBox.getId())) {
          Button taskBox = createNewTask(t);
          dayThreeTasksBox.getChildren().add(taskBox);
        } else if (String.valueOf(t.getDay()).toLowerCase().equals(dayFourBox.getId())) {
          Button taskBox = createNewTask(t);
          dayFourTasksBox.getChildren().add(taskBox);
        } else if (String.valueOf(t.getDay()).toLowerCase().equals(dayFiveBox.getId())) {
          Button taskBox = createNewTask(t);
          dayFiveTasksBox.getChildren().add(taskBox);
        } else if (String.valueOf(t.getDay()).toLowerCase().equals(daySixBox.getId())) {
          Button taskBox = createNewTask(t);
          daySixTasksBox.getChildren().add(taskBox);
        } else if (String.valueOf(t.getDay()).toLowerCase().equals(daySevenBox.getId())) {
          Button taskBox = createNewTask(t);
          daySevenTasksBox.getChildren().add(taskBox);
        }
      }
    }
  }


  /**
   * Creates a new task display from the given task.
   *
   * @param t the task to be displayed
   * @return the task as a button
   */
  private Button createNewTask(Task t) {
    Button taskButton = new Button();
    Font font = Font.font("Avenir Book",
        FontWeight.NORMAL, 13);

    taskButton.setText("- " + t.getName());
    taskButton.setFont(font);
    taskButton.setOnAction(
        e -> switchScene(taskButton, new ViewTaskController(t, this.week), "viewTask.fxml"));
    taskButton.wrapTextProperty().setValue(true);
    taskButton.setPrefWidth(169);
    taskButton.setMinHeight(26);
    taskButton.setMaxHeight(50);
    taskButton.setTextAlignment(TextAlignment.LEFT);
    taskButton.setAlignment(Pos.TOP_LEFT);
    String regularButton = "-fx-background-color: #DBDFAA;";
    taskButton.setStyle(regularButton);
    String buttonWhenMouseHovers =
        "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, "
            + "-fx-inner-border, -fx-body-color;";
    taskButton.setOnMouseEntered(e -> taskButton.setStyle(buttonWhenMouseHovers));
    taskButton.setOnMouseExited(e -> taskButton.setStyle(regularButton));
    return taskButton;
  }

  /**
   * Converts the week events to be displayable.
   */
  private void convertWeekEventsToGui() {
    for (Day d : this.week.getDays()) {
      for (Event e : d.getEvents()) {
        if (String.valueOf(e.getDay()).toLowerCase().equals(dayOneBox.getId())) {
          Button eventBox = createNewEvent(e);
          dayOneEventsBox.getChildren().add(eventBox);
        } else if (String.valueOf(e.getDay()).toLowerCase().equals(dayTwoBox.getId())) {
          Button eventBox = createNewEvent(e);
          dayTwoEventsBox.getChildren().add(eventBox);
        } else if (String.valueOf(e.getDay()).toLowerCase().equals(dayThreeBox.getId())) {
          Button eventBox = createNewEvent(e);
          dayThreeEventsBox.getChildren().add(eventBox);
        } else if (String.valueOf(e.getDay()).toLowerCase().equals(dayFourBox.getId())) {
          Button eventBox = createNewEvent(e);
          dayFourEventsBox.getChildren().add(eventBox);
        } else if (String.valueOf(e.getDay()).toLowerCase().equals(dayFiveBox.getId())) {
          Button eventBox = createNewEvent(e);
          dayFiveEventsBox.getChildren().add(eventBox);
        } else if (String.valueOf(e.getDay()).toLowerCase().equals(daySixBox.getId())) {
          Button eventBox = createNewEvent(e);
          daySixEventsBox.getChildren().add(eventBox);
        } else if (String.valueOf(e.getDay()).toLowerCase().equals(daySevenBox.getId())) {
          Button eventBox = createNewEvent(e);
          daySevenEventsBox.getChildren().add(eventBox);
        }
      }
    }
  }


  /**
   * Creates a new event display from a given event.
   *
   * @param e the event to be displayed
   * @return a button representation of the event
   */
  private Button createNewEvent(Event e) {
    Button eventButton = new Button();
    eventButton.setText("- " + e.getName());
    Font font = Font.font("Avenir Book",
        FontWeight.NORMAL, 13);
    eventButton.setFont(font);
    eventButton.setOnAction(
        event -> switchScene(eventButton, new ViewEventController(e, this.week), "viewEvent.fxml"));
    eventButton.wrapTextProperty().setValue(true);
    eventButton.setPrefWidth(169);
    eventButton.setMinHeight(26);
    eventButton.setMaxHeight(50);
    eventButton.setAlignment(Pos.TOP_LEFT);
    eventButton.setTextAlignment(TextAlignment.LEFT);
    eventButton.setStyle("-fx-background-color: #B3C890; ");
    String buttonWhenMouseHovers =
        "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, "
            + "-fx-inner-border, -fx-body-color;";
    eventButton.setOnMouseEntered(event -> eventButton.setStyle(buttonWhenMouseHovers));
    String regularButton = "-fx-background-color: #B3C890;";
    eventButton.setOnMouseExited(event -> eventButton.setStyle(regularButton));

    return eventButton;
  }


  /**
   * Saves this Bujo file to the given file path that is entered from the user.
   */
  private void newFileCreation() {
    try {
      new CreateNewFile().createNewFile(week, week.getName());
    } catch (IOException e) {
      throw new NoSuchElementException("this bujo file cannot be saved with the given file path");
    }
  }


  /**
   * Updates all the progress bars to reflect complete vs incomplete tasks.
   */
  private void updateProgressBars() {
    for (Day day : this.week.getDays()) {
      if (String.valueOf(day.getDayOfWeek()).toLowerCase().equals(dayOneBox.getId())) {
        this.dayOneProgLabel.setText("progress: " + day.getNumOfCompletedTasks()
            + "/" + day.getTasks().size());
        this.dayOneProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (String.valueOf(day.getDayOfWeek()).toLowerCase().equals(dayTwoBox.getId())) {
        this.dayTwoProgLabel.setText("progress: " + day.getNumOfCompletedTasks()
            + "/" + day.getTasks().size());
        this.dayTwoProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (String.valueOf(day.getDayOfWeek()).toLowerCase().equals(dayThreeBox.getId())) {
        this.dayThreeProgLabel.setText("progress: " + day.getNumOfCompletedTasks()
            + "/" + day.getTasks().size());
        this.dayThreeProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (String.valueOf(day.getDayOfWeek()).toLowerCase().equals(dayFourBox.getId())) {
        this.dayFourProgLabel.setText("progress: " + day.getNumOfCompletedTasks()
            + "/" + day.getTasks().size());
        this.dayFourProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (String.valueOf(day.getDayOfWeek()).toLowerCase().equals(dayFiveBox.getId())) {
        this.dayFiveProgLabel.setText("progress: " + day.getNumOfCompletedTasks()
            + "/" + day.getTasks().size());
        this.dayFiveProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (String.valueOf(day.getDayOfWeek()).toLowerCase().equals(daySixBox.getId())) {
        this.daySixProgLabel.setText("progress: " + day.getNumOfCompletedTasks()
            + "/" + day.getTasks().size());
        this.daySixProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (String.valueOf(day.getDayOfWeek()).toLowerCase().equals(daySevenBox.getId())) {
        this.daySevenProgLabel.setText("progress: " + day.getNumOfCompletedTasks()
            + "/" + day.getTasks().size());
        this.daySevenProgressBar.setProgress(day.taskCompletionPercentage());
      }
    }
  }

  /**
   * Initializes all shortcuts for this program.
   */
  private void settingShortcuts() {
    setAccelerators();
    this.menuBar.getScene().getAccelerators()
        .put(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN),
            this::newFileCreation);
    this.menuBar.getScene().getAccelerators()
        .put(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN),
            () -> switchScene(this.menuBar, new OpenExistingFileController(),
                "openExistingFile.fxml"));
    this.menuBar.getScene().getAccelerators()
        .put(new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN),
            this::newEvent);
    this.menuBar.getScene().getAccelerators()
        .put(new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN),
            this::newTask);
    this.menuBar.getScene().getAccelerators()
        .put(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN),
            () -> switchScene(this.menuBar, new CreateNewFileController(), "createNewFile.fxml"));
    this.menuBar.getScene().getAccelerators()
        .put(new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN),
            () -> {
              switchScene(this.menuBar, new ChangeWeekNameController(this.week),
                  "enterWeekName.fxml");
              mutateJournalName();
            });
    this.menuBar.getScene().getAccelerators()
        .put(new KeyCodeCombination(KeyCode.L, KeyCombination.SHORTCUT_DOWN),
            Platform::exit);
    this.menuBar.getScene().getAccelerators()
        .put(new KeyCodeCombination(KeyCode.P, KeyCombination.SHORTCUT_DOWN),
            () -> switchScene(this.menuBar, new OpenFileAsTemplateController(),
                "openFileAsTemplate.fxml"));
    this.menuBar.getScene().getAccelerators()
        .put(new KeyCodeCombination(KeyCode.M, KeyCombination.SHORTCUT_DOWN), () ->
            switchScene(this.menuBar, new EditMaxTasksAndEventsController(this.week),
                "editMaxTasksAndEvents.fxml"));
  }

  /**
   * Initializes all hot keys for this program.
   */
  private void setAccelerators() {
    renameWeekButton.setAccelerator(
        new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN));
    openFileAsTemplateButton.setAccelerator(
        new KeyCodeCombination(KeyCode.P, KeyCombination.SHORTCUT_DOWN));
    newWeek.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
    editMaxTasksAndEventsButton.setAccelerator(
        new KeyCodeCombination(KeyCode.M, KeyCombination.SHORTCUT_DOWN));
    newEvent.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN));
    newTask.setAccelerator(new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN));
    openExistingButton.setAccelerator(new KeyCodeCombination(KeyCode.O,
        KeyCombination.SHORTCUT_DOWN));
    saveButton.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
  }
}

