package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a graphical view of a bullet journal.
 */
public class GraphicalView implements View {

  private final FXMLLoader loader;

  /**
   * Initializes a Graphical view from an FXML file.
   *
   * @param controller the interactions for this view
   * @param fxmlFileName the file with the view
   */
  public GraphicalView(Controller controller, String fxmlFileName) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(fxmlFileName));
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from a Bullet Journal GUI layout.
   *
   * @return the layout
   */
  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
