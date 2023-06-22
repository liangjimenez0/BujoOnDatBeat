package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Handles the expanding of an event in the view.
 */
public class ViewEventController extends AbstractController {

  private Event currentEvent;
  @FXML
  private TextField userEventName;
  @FXML
  private TextField userEventDay;
  @FXML
  private TextField userEventStartTime;
  @FXML
  private TextField userEventDuration;
  @FXML
  private TextArea userEventDescription;
  @FXML
  private Button deleteEvent;
  @FXML
  private Button doneViewing;
  private Week week;

  /**
   * Initializes an object that can view and edit an event in a given week.
   *
   * @param event the event being expanded
   * @param week  the current week
   */
  public ViewEventController(Event event, Week week) {
    this.currentEvent = event;
    this.week = week;
  }

  /**
   * Handles different button presses.
   */
  public void run() {
    this.deleteEvent.getScene().getWindow().centerOnScreen();
    setTextFields();
    deleteEvent.setOnAction(e -> deleteEvent());
    doneViewing.setOnAction(
        e -> editEvent());

  }

  /**
   * Updates an event to contain the new desired information.
   */
  private void editEvent() {
    for (Day d : this.week.getDays()) {
      for (Event e : d.getEvents()) {

        // updates the event to capture any changes made
        if (e.equals(this.currentEvent)) {
          e.changeName(userEventName.getText());
          e.changeStartTime(Long.parseLong(userEventStartTime.getText()));
          e.changeDuration(Integer.parseInt(userEventDuration.getText()));
          e.changeDescription(userEventDescription.getText());
          e.changeDay(DayOfWeek.valueOf(userEventDay.getText().toUpperCase()));

          // if the day has been changed, remove the event from its current day
          // and add to the correct day.
          if (e.getDay() != d.getDayOfWeek()) {
            d.getEvents().remove(e);
            for (Day newDay : this.week.getDays()) {
              if (e.getDay() == newDay.getDayOfWeek()) {
                newDay.getEvents().add(e);
              }
            }
          }
        }
      }
    }
    switchScene(doneViewing, new WeekViewController(this.week), "weekView.fxml");
  }

  /**
   * Shows the user the current information in the event.
   */
  private void setTextFields() {
    userEventDay.setText(String.valueOf(currentEvent.getDay()));
    userEventName.setText(currentEvent.getName());
    userEventStartTime.setText(String.valueOf(currentEvent.getStartTime()));

    if (currentEvent.getDescription() != null) {
      userEventDescription.setText(currentEvent.getDescription());
    }
    userEventDuration.setText(String.valueOf(currentEvent.getDuration()));
  }


  /**
   * Deletes the event in this controller.
   */
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
