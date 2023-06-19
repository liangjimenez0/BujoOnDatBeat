package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

public class CreateNewEventController extends AbstractController {
  @FXML
  private TextField userEventName, userEventDay, userEventStartTime, userEventDuration;

  @FXML
  private TextArea userEventDescription;

  @FXML
  private Button newEventDone;

  private Week currentWeek;

  public CreateNewEventController(Week currentWeek) {
    this.currentWeek = currentWeek;
  }

  public void run() {
    newEventDone.setOnAction(e -> createNewEvent());
  }

  private void createNewEvent() {
    String eventName = userEventName.getText();
    DayOfWeek weekday = DayOfWeek.valueOf(userEventDay.getText().toUpperCase());
    Event newEvent;
    Long eventStartTime = Long.parseLong(userEventStartTime.getText());
    int eventDuration = Integer.parseInt(userEventDuration.getText());

    if (userEventDescription.getText() != null) {
      String eventDescription = userEventDescription.getText();
      newEvent = new Event(eventName, weekday, eventDescription, eventStartTime, eventDuration);
    } else {
      newEvent = new Event(eventName, weekday, eventStartTime, eventDuration);
    }

    addEventToWeek(newEvent);

    switchScene(newEventDone, new WeekViewController(this.currentWeek), "weekView.fxml");
  }

  private void addEventToWeek(Event newEvent) {

    for (Day d : this.currentWeek.getDays()) {

      if (newEvent.getDay().equals(d.getDayOfWeek())) {
        d.getEvents().add(newEvent);
      }
    }
  }
}
