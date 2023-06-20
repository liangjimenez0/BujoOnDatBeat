package cs3500.pa05.controller;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;
import java.io.FileInputStream;

public class SplashSceneController extends AbstractController {
  @FXML
  ImageView bulletJournalLogo;

  @FXML
  Button hidden;

  Scene gifLoaded;

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

    Group root = new Group(bulletJournalLogo);
    gifLoaded = new Scene(root, 595, 370);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }

    switchScene(hidden,
        new WelcomeController(), "welcomePage.fxml");
  }

  public Scene returnGif() {
    return gifLoaded;
  }
}
