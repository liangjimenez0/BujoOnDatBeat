package cs3500.pa05.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Handles displaying a splash scene to the user.
 */
public class SplashSceneController extends AbstractController {
  @FXML
  private ImageView bulletJournalLogo;
  @FXML
  private Button hidden;
  private Stage stage;

  /**
   * Initializes an object that can display a splash scene.
   *
   * @param stage where to display the scene.
   */
  public SplashSceneController(Stage stage) {
    this.stage = stage;
  }

  /**
   * Displays the scene until a user clicks the screen.
   */
  @Override
  public void run() {
    InputStream stream = null;
    try {
      stream = new FileInputStream("src/main/resources/bujo.gif");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Image image = new Image(stream);
    bulletJournalLogo.setImage(image);
    bulletJournalLogo.setFitHeight(700.0);
    bulletJournalLogo.setFitWidth(900.0);
    bulletJournalLogo.setPreserveRatio(true);
    hidden.setOnAction(e -> switchScene(hidden,
        new WelcomeController(), "welcomePage.fxml"));
  }
}
