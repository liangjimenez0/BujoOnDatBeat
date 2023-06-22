package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.SplashSceneController;
import cs3500.pa05.view.GraphicalView;
import cs3500.pa05.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point to this project
 */
public class Driver extends Application {

  /**
   * The entry method
   *
   * @param args are the command line arguments that are to be processed and read
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * Starts this application.
   *
   * @param stage the primary stage for this application, onto which
   *              the application scene can be set.
   *              Applications may create other stages, if needed, but they will not be
   *              primary stages.
   */
  @Override
  public void start(Stage stage) {
    Controller controller = new SplashSceneController(stage);
    View view = new GraphicalView(controller, "splashScene.fxml");

    try {
      stage.setScene(view.load());

      controller.run();

      stage.show();

    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }
}
