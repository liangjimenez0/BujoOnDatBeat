package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.Week;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Handles interaction with the week view between the user and the model representation
 */
public class WeekViewController extends AbstractController {

//  @FXML
//  private Button viewMenu;

  /**
   * Runs the JavaFX scene based on a week's data of tasks and events
   */
  @Override
  public void run() {

    convertWeekToGui();

    // saves file
    saveItem.setOnAction(e -> newFileCreation());

    // opens an exisiting file and updates week
    openExistingItem.setOnAction(e -> switchScene(done, new OpenExistingFileController(), "openExistingFile.fxml"));

    // create new task
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("createNewTask.fxml"));
    loader.setController(this);
    try {

      Scene s = loader.load();
      this.createNewTaskPopup.getContent().add(s.getRoot());
      this.newTask.setOnAction(e -> this.createNewTaskPopup.hide());

    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  private void convertWeekToGui() {}


  private void makeTaskPopup() {
    createNewTaskPopup.show(this.stage);
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
