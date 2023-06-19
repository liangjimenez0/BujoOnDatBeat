package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import cs3500.pa05.view.GraphicalView;
import cs3500.pa05.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.stage.Stage;

/**
 * Represents the abstracted code from controller that switches scenes on a JavaFX stage
 */
public abstract class AbstractController implements Controller {
  private Week currentWeek;

  /**
   * @param controller is the given Controller that handles the switching of scenes on a stage
   * @param fxmlFileName is the file name of the given JavaFX file
   */
  public void switchScene(Control control, Controller controller, String fxmlFileName) {

    Stage stage = (Stage) control.getScene().getWindow();
    stage.fullScreenProperty();

    View view = new GraphicalView(controller, fxmlFileName);

    stage.setScene(view.load());

    controller.run();

    stage.show();
  }

  /**
   * Initializes a Java Journal
   */
  public abstract void run();
}
