package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.WelcomeController;
import cs3500.pa05.view.GraphicalView;
import cs3500.pa05.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point to this project
 */
public class Driver extends Application {

  /**
   * @param args are the command line arguments that are to be processed and read
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * @param stage the primary stage for this application, onto which
   *              the application scene can be set.
   *              Applications may create other stages, if needed, but they will not be
   *              primary stages.
   * @throws Exception when this method is interrupted
   */
  @Override
  public void start(Stage stage) {

    Controller controller = new WelcomeController();
    View view = new GraphicalView(controller, "welcomePage.fxml");

    try {
      stage.setScene(view.load());

      controller.run();

      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }
}
