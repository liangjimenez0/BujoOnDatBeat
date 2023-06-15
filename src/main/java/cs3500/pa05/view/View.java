package cs3500.pa05.view;

import javafx.scene.Scene;

public interface View {
  /**
   * Loads a scene from a Bullet Journal GUI layout.
   *
   * @return the layout
   */
  Scene load() throws IllegalStateException;
}
