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
 * Handles the creation of a new event.
 */
public class CreateNewEventController extends AbstractController {
  @FXML
  private TextField userEventName, userEventDay, userEventStartTime, userEventDuration;

  @FXML
  private TextArea userEventDescription;

  @FXML
  private Button newEventDone, backToWeekView;

  private Week currentWeek;

  /**
   * Initializes an object that can create a new event within the given week.
   *
   * @param currentWeek the week to be updated with the new event created
   */
  public CreateNewEventController(Week currentWeek) {
    this.currentWeek = currentWeek;
  }

  /**
   * Handles creating a new event when the user presses the done button.
   */
  public void run() {
    newEventDone.setOnAction(e -> createNewEvent());
    backToWeekView.setOnAction(e -> switchScene(newEventDone,
        new WeekViewController(this.currentWeek), "weekView.fxml"));
  }

  /**
   * Creates a new event from the inputted information.
   */
  private void createNewEvent() {
    String eventName = userEventName.getText();
    Event newEvent;

    try {
      long eventStartTime = Long.parseLong(userEventStartTime.getText());
      int eventDuration = Integer.parseInt(userEventDuration.getText());
      if (userEventDescription.getText() != null) {
        if (!this.currentWeek.checkDay(userEventDay.getText().toUpperCase())) {
          switchScene(newEventDone, new WarningController(this.currentWeek),
              "invalidWidgetWarning.fxml");
        }
        DayOfWeek weekday = DayOfWeek.valueOf(userEventDay.getText().toUpperCase());
        for (Day day : this.currentWeek.getDays()) {
          if (day.getDayOfWeek() == weekday) {
            if (day.getEvents().size() + 1 > this.currentWeek.getMaxEvents()) {
              switchScene(newEventDone, new WarningController(this.currentWeek),
                  "warningScreen.fxml");
            } else {
              String eventDescription = userEventDescription.getText();
              newEvent =
                  new Event(eventName, weekday, eventDescription, eventStartTime, eventDuration);
              addEventToWeek(newEvent);
              switchScene(newEventDone, new WeekViewController(this.currentWeek), "weekView.fxml");
            }
          }
        }
      }
    } catch (NumberFormatException e) {
      switchScene(newEventDone, new WarningController(this.currentWeek),
          "invalidWidgetWarning.fxml");
    }
  }


  /**
   * Adds a given event to the correct day in the week.
   *
   * @param newEvent the event to be added to the week.
   */
  private void addEventToWeek(Event newEvent) {

    for (Day d : this.currentWeek.getDays()) {

      if (newEvent.getDay().equals(d.getDayOfWeek())) {
        d.getEvents().add(newEvent);
      }
    }
  }
}
