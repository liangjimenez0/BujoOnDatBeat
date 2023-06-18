package cs3500.pa05.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

public class CreateNewEventController extends AbstractController {
  @FXML
  private TextField userEventName, userEventDay, userDescription, userStartTime, userDuration;

  @FXML
  private Button newEventDone;

  private Popup createNewEventPopup = new Popup();

  private Control c;

  public CreateNewEventController(Control c) {
    this.c = c;
  }


  public void run() {
    makeEventPopup();
    eventCreation();
  }

  private void makeEventPopup() {
    createNewEventPopup.show(this.c.getScene().getWindow());
  }

  private void eventCreation() {
    FXMLLoader eventLoader =
        new FXMLLoader(getClass().getClassLoader().getResource("createNewEvent.fxml"));
    eventLoader.setController(this);
    try {
      Scene s = eventLoader.load();
      this.createNewEventPopup.getContent().add(s.getRoot());
      this.newEventDone.setOnAction(e -> this.createNewEventPopup.hide());

      //this.newEventDone.setOnAction(e -> convertWeekEventsToGui());

    } catch (IOException e) {
      throw new RuntimeException();
    }
  }
}
