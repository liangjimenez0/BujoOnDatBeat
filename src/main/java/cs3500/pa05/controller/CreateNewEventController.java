package cs3500.pa05.controller;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

public class CreateNewEventController extends AbstractController{
  @FXML
  private TextField userEventName, userEventDay, userDescription, userStartTime, userDuration;

  public Event newlyCreatedEvent;

  private Control c;

  @FXML
  private Button newEventDone;

  private Popup createNewEventPopup = new Popup();

  public CreateNewEventController(Control c) {
    this.c = c;

  }

  public void run() {
    String eventName = userEventName.getText();
    DayOfWeek weekday = DayOfWeek.valueOf(userEventDay.getText().toUpperCase());
    String eventDescription = userDescription.getText();
    Long eventStartTime = Long.parseLong(userStartTime.getText());
    int eventDuration = Integer.parseInt(userDuration.getText());

    this.newlyCreatedEvent = new Event(eventName, weekday,
        eventDescription, eventStartTime, eventDuration);
  }

  public Event getNewlyCreatedEvent() {
    return this.newlyCreatedEvent;
  }


  private void eventCreation() {
    FXMLLoader eventLoader =
        new FXMLLoader(getClass().getClassLoader().getResource("createNewEvent.fxml"));
    eventLoader.setController(this);

    try {
      Scene s = eventLoader.load();
      this.createNewEventPopup.getContent().add(s.getRoot());
      this.newEventDone.setOnAction(e -> this.createNewEventPopup.hide());

    } catch (IOException e) {
      throw new RuntimeException();
    }
  }


  private void makeEventPopup() {
    createNewEventPopup.show(this.c.getScene().getWindow());
  }
}
