package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Represents all the methods that a view must implement.
 */
public interface View {
  /**
   * Loads a scene from a Bullet Journal GUI layout.
   *
   * @return the layout
   */
  Scene load() throws IllegalStateException;
}
