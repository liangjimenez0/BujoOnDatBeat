package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import cs3500.pa05.view.GraphicalView;
import cs3500.pa05.view.View;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class AbstractController implements Controller {

  private Week currentWeek;
  Button createNewFile;

  public void switchScene(Controller controller, String fxmlFileName) {
    Stage stage = (Stage) createNewFile.getScene().getWindow();
    View view = new GraphicalView(controller, fxmlFileName);

    stage.setScene(view.load());
    stage.show();

    controller.run();
  }

  public abstract void run();

}
