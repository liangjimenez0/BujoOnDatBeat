package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
  private Week week;

  @FXML
  private Label bujoTitle;

  @FXML
  private Button newTaskButton, newEventButton;

  @FXML
  private VBox sunTasksBox, sunEventsBox, monTasksBox, monEventsBox, tuesTasksBox, tuesEventsBox,
      wedTasksBox, wedEventsBox, thursTasksBox, thursEventsBox, friTasksBox, friEventsBox,
      satTasksBox, satEventsBox, allTasksBox;
  @FXML
  private MenuBar menuBar;

  @FXML
  private MenuItem saveButton, openExistingButton, newTask, newEvent, newWeek, renameWeekButton;
  private CreateNewTaskController taskController;
  @FXML
  private TextField userTaskName, searchBar;
  @FXML
  private ProgressBar sunProgressBar, monProgressBar, tuesProgressBar, wedProgressBar,
      thursProgressBar,
      friProgressBar, satProgressBar;
  private List<CheckBox> allCheckBoxes = new ArrayList<>();

  private ListView<String> listOfSearches;
  private String newName;

  public WeekViewController(Week week) {
    this.week = week;

  }

  public WeekViewController(Week week, String s) {
    this.week = week;
    this.newName = s;
    //this.bujoTitle.setText(s);
  }

  /**
   * Runs the JavaFX scene based on a week's data of tasks and events
   */
  @Override
  public void run() {
    this.menuBar.getScene().getWindow().centerOnScreen();
    settingShortcuts();
    createTaskQueue();
    convertWeekTasksToGui();
    convertWeekEventsToGui();
    checkIfUserMarkedAsCompleted();
    updateProgressBars();
   // searchAlgorithm();

    saveButton.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
    saveButton.setOnAction(e -> newFileCreation());

    openExistingButton.setAccelerator(new KeyCodeCombination(KeyCode.O,
        KeyCombination.SHORTCUT_DOWN));

    openExistingButton.setOnAction(e -> {
      switchScene(this.menuBar,
          new OpenExistingFileController(), "openExistingFile.fxml");
      newFileCreation();
    });

    newTask.setAccelerator(new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN));
    newTask.setOnAction(e -> newTask());
    newTaskButton.setOnAction(e -> newTask());

    newEvent.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN));
    newEvent.setOnAction(e -> newEvent());
    newEventButton.setOnAction(e -> newEvent());

    newWeek.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
    newWeek.setOnAction(
        e -> {
          switchScene(this.menuBar, new CreateNewFileController(), "createNewFile.fxml");
          newFileCreation();
        });

    renameWeekButton.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN));
    renameWeekButton.setOnAction(e -> {
      switchScene(this.menuBar,
          new ChangeWeekNameController(this.week), "enterWeekName.fxml");
      this.bujoTitle.setText(newName);

    });

  }

  private void checkIfUserMarkedAsCompleted() {
    if (allCheckBoxes.size() > 0) {
      for (CheckBox c : allCheckBoxes) {
        if (c.isSelected()) {
          for (Day d : this.week.getDays()) {
            for (Task t : d.getTasks()) {
              if (c.getText().equals(t.getName())) {
                t.changeCompleted(true);
              }
            }
          }
        }
      }
    }
  }

//  private void searchAlgorithm() {
//    ArrayList<String> allTaskNames = new ArrayList<>();
//    for (Task t : this.week.getAllTasks()) {
//      allTaskNames.add(t.getName());
//    }
//    listOfSearches.getItems().addAll(searchList(searchBar.getText(), allTaskNames));
//
//    if (searchBar.getText() != null) {
//      listOfSearches.getItems().addAll(allTaskNames);
//    }
//  }
//
//  private List<String> searchList(String searchWords, List<String> listOfStrings) {
//    List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
//
//    return listOfStrings.stream().filter(input -> {
//      return searchWordsArray.stream().allMatch(word ->
//          input.toLowerCase().contains(word.toLowerCase()));
//    }).collect(Collectors.toList());
//  }




  private void newTask() {
    switchScene(this.menuBar, new CreateNewTaskController(this.week), "createNewTask.fxml");
  }

  private void newEvent() {
    switchScene(this.menuBar, new CreateNewEventController(this.week), "createNewEvent.fxml");
  }


  private void createTaskQueue() {
    List<Task> allTasks = this.week.getAllTasks();

    allTasksBox.getChildren().clear();

    for (Task t : allTasks) {
      CheckBox taskCheckBox = convertTaskToGui(t);
      allTasksBox.getChildren().add(taskCheckBox);
      allCheckBoxes.add(taskCheckBox);
    }
  }


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
    String regularButton = "-fx-background-color: #DBDFAA;";
    String buttonWhenMouseHovers =
        "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";

    taskButton.setText("- " + t.getName());
    taskButton.setFont(font);
    taskButton.setOnAction(
        e -> switchScene(taskButton, new ViewTaskController(t, this.week), "viewTask.fxml"));
    taskButton.wrapTextProperty().setValue(true);
    taskButton.setPrefWidth(169);
    taskButton.setMinHeight(26);
    taskButton.setMaxHeight(50);
    taskButton.setTextAlignment(TextAlignment.LEFT);
    taskButton.setStyle(regularButton);
    taskButton.setOnMouseEntered(e -> taskButton.setStyle(buttonWhenMouseHovers));
    taskButton.setOnMouseExited(e -> taskButton.setStyle(regularButton));

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
    String regularButton = "-fx-background-color: #B3C890;";
    String buttonWhenMouseHovers =
        "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";

    eventButton.setText("- " + e.getName());
    eventButton.setFont(font);
    eventButton.setOnAction(
        event -> switchScene(eventButton, new ViewEventController(e, this.week), "viewEvent.fxml"));
    eventButton.wrapTextProperty().setValue(true);
    eventButton.setPrefWidth(169);
    eventButton.setMinHeight(26);
    eventButton.setMaxHeight(50);
    eventButton.setTextAlignment(TextAlignment.LEFT);
    eventButton.setStyle("-fx-background-color: #B3C890; ");
    eventButton.setOnMouseEntered(event -> eventButton.setStyle(buttonWhenMouseHovers));
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


  private void updateProgressBars() {
    for (Day day : this.week.getDays()) {
      if (day.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
        this.sunProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (day.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
        this.monProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (day.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
        this.tuesProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (day.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
        this.wedProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (day.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
        this.thursProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (day.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
        this.friProgressBar.setProgress(day.taskCompletionPercentage());
      } else if (day.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
        this.satProgressBar.setProgress(day.taskCompletionPercentage());
      }
    }
  }


  private void settingShortcuts() {
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
  }
}
