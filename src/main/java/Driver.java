import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.ControllerImpl;
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

    WamBoard board = new BoardImpl();
    Controller controller = new ControllerImpl(board);
    View view = new GraphicalView(controller);

    try {
      stage.setScene(view.load());

      controller.run();

      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }

  }
}
