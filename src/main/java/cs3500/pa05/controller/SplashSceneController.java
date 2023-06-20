package cs3500.pa05.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SplashSceneController extends AbstractController {
  @FXML
  ImageView bulletJournalLogo;

  @FXML
  Button hidden;

  Stage stage;

  public SplashSceneController(Stage stage) {
    this.stage = stage;
  }

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
    bulletJournalLogo.setPreserveRatio(true);

    hidden.setOnAction(e -> switchScene(hidden,
        new WelcomeController(), "welcomePage.fxml"));
  }
}
