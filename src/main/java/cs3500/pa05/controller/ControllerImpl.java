package cs3500.pa05.controller;

import cs3500.pa05.model.CreateNewFile;
import cs3500.pa05.model.ReadFile;
import cs3500.pa05.model.Week;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.util.List;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerImpl implements Controller {

  Week currentWeek;
  List<Integer> maxTasks;
  List<Integer> maxEvents;
  @FXML
  private Button createNewFile, openExistingFile;
  @FXML
  private Scene createNewFileScene;




  @Override
  public void run() {

    createNewFile.setOnAction(
        e -> setNewScene(createNewFile, createNewFileScene));
    openExistingFile.setOnAction(e -> currentWeek.processWeek());

  }

  private void setNewScene(Button button, Scene scene) {
    Stage stage = (Stage) button.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

}
