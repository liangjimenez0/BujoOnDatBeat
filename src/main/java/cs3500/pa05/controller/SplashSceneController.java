package cs3500.pa05.controller;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SplashSceneController extends AbstractController {
  @FXML
  ImageView bulletJournalLogo;

  @FXML
  Button hidden;

  @Override
  public void run() {
    //Image image1 =
    //    new Image(SplashSceneController.class.getResourceAsStream("Bullet Jouraling.gif"));
    //bulletJournalLogo.setImage(image1);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }

    switchScene(hidden,
        new WelcomeController(), "welcomePage.fxml");
  }
  }
