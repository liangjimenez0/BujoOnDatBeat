package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ViewEventController extends AbstractController {

  Event currentEvent;
  @FXML
  private TextField userEventName, userEventDay, userEventStartTime, userEventDuration;

  @FXML
  private TextArea userEventDescription;
  @FXML
  Button deleteEvent, doneViewing;
  Week week;

  public ViewEventController(Event event, Week week) {
    this.currentEvent = event;
    this.week = week;
  }

  public void run() {

    setTextFields();

    deleteEvent.setOnAction(e -> deleteEvent());
    doneViewing.setOnAction(
        e -> editEvent());

  }

  private void editEvent() {

    for (Day d : this.week.getDays()) {

      for (Event e : d.getEvents()) {

        if (e.equals(this.currentEvent)) {
          e.changeName(userEventName.getText());
          e.changeStartTime(Long.parseLong(userEventStartTime.getText()));
          e.changeDuration(Integer.parseInt(userEventDuration.getText()));
          e.changeDescription(userEventDescription.getText());

          e.changeDay(DayOfWeek.valueOf(userEventDay.getText().toUpperCase()));
        }

      }

    }

    switchScene(doneViewing, new WeekViewController(this.week), "weekView.fxml");
  }

  private void setTextFields() {

    userEventDay.setText(String.valueOf(currentEvent.getDay()));
    userEventName.setText(currentEvent.getName());
    userEventStartTime.setText(String.valueOf(currentEvent.getStartTime()));

    if (currentEvent.getDescription() != null) {
      userEventDescription.setText(currentEvent.getDescription());
    }

    userEventDuration.setText(String.valueOf(currentEvent.getDuration()));
  }

  private void deleteEvent() {

    for (Day d : this.week.getDays()) {

      for (Event e : d.getEvents()) {

        if (e.equals(this.currentEvent)) {
          d.getEvents().remove(e);
        }

      }
    }

    switchScene(deleteEvent, new WeekViewController(this.week), "weekView.fxml");
  }


}
