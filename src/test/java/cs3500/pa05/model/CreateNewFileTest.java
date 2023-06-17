package cs3500.pa05.model;

import cs3500.pa05.json.JsonBujoFile;
import cs3500.pa05.json.JsonDay;
import cs3500.pa05.json.JsonEvent;
import cs3500.pa05.json.JsonTask;
import cs3500.pa05.json.JsonWeek;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the test class that writes to a new Bujo file.
 */
class CreateNewFileTest {
  CreateNewFile testCreateFile;
  int maxTasks;
  int maxEvents;
  Week exampleWeek;
  Task exampleTaskSunday = new Task("this is a task", DayOfWeek.SUNDAY, "this must be completed");
  Task exampleTaskMonday = new Task("this is a task", DayOfWeek.MONDAY, "this must be completed");
  Task exampleTaskTuesday = new Task("this is a task", DayOfWeek.TUESDAY, "this must be completed");
  Task exampleTaskWed = new Task("this is a task", DayOfWeek.WEDNESDAY, "this must be completed");
  Task exampleTaskThu = new Task("this is a task", DayOfWeek.THURSDAY, "this must be completed");
  Task exampleTaskFri = new Task("this is a task", DayOfWeek.FRIDAY, "this must be completed");
  Task exampleTaskSat = new Task("this is a task", DayOfWeek.SATURDAY, "this must be completed");

  Event exampleEventSunday = new Event("this is an event", DayOfWeek.SUNDAY,
      "this must be completed", 0, 20);
  Event exampleEventMonday = new Event("this is an event", DayOfWeek.MONDAY,
      "this must be completed", 0, 20);
  Event exampleEventTuesday = new Event("this is an event", DayOfWeek.TUESDAY,
      "this must be completed", 0, 20);
  Event exampleEventWed = new Event("this is an event", DayOfWeek.WEDNESDAY,
      "this must be completed", 0, 20);
  Event exampleEventThu = new Event("this is an event", DayOfWeek.THURSDAY,
      "this must be completed", 0, 20);
  Event exampleEventFri = new Event("this is an event", DayOfWeek.FRIDAY,
      "this must be completed", 0, 20);
  Event exampleEventSat = new Event("this is an event", DayOfWeek.SATURDAY,
      "this must be completed", 0, 20);

  /**
   * Called before each test method that initializes objects used during testing.
   */
  @BeforeEach
  public void setup() {
    this.testCreateFile = new CreateNewFile();
    this.maxTasks = 3;
    this.maxEvents = 5;
    this.exampleWeek = new Week(this.maxTasks, this.maxEvents);
    this.exampleWeek.getDay(DayOfWeek.SUNDAY).addToTask(exampleTaskSunday);
    this.exampleWeek.getDay(DayOfWeek.MONDAY).addToTask(exampleTaskMonday);
    this.exampleWeek.getDay(DayOfWeek.TUESDAY).addToTask(exampleTaskTuesday);
    this.exampleWeek.getDay(DayOfWeek.WEDNESDAY).addToTask(exampleTaskWed);
    this.exampleWeek.getDay(DayOfWeek.THURSDAY).addToTask(exampleTaskThu);
    this.exampleWeek.getDay(DayOfWeek.FRIDAY).addToTask(exampleTaskFri);
    this.exampleWeek.getDay(DayOfWeek.SATURDAY).addToTask(exampleTaskSat);

    this.exampleWeek.getDay(DayOfWeek.SUNDAY).addToEvent(exampleEventSunday);
    this.exampleWeek.getDay(DayOfWeek.MONDAY).addToEvent(exampleEventMonday);
    this.exampleWeek.getDay(DayOfWeek.TUESDAY).addToEvent(exampleEventTuesday);
    this.exampleWeek.getDay(DayOfWeek.WEDNESDAY).addToEvent(exampleEventWed);
    this.exampleWeek.getDay(DayOfWeek.THURSDAY).addToEvent(exampleEventThu);
    this.exampleWeek.getDay(DayOfWeek.FRIDAY).addToEvent(exampleEventFri);
    this.exampleWeek.getDay(DayOfWeek.SATURDAY).addToEvent(exampleEventSat);
  }


  /**
   * Testing the method createNewFile that writes the appropriate Bujo file contents
   * to the given file.
   */
  @Test
  void createNewFile() {
    try {
      this.testCreateFile.createNewFile(this.exampleWeek,
          "src/test/testfiles/createFileTestOutput.bujo");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}