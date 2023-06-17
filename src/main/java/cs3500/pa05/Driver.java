package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.WelcomeController;
import cs3500.pa05.view.GraphicalView;
import cs3500.pa05.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {

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
