package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the test class that writes to a new Bujo file.
 */
class CreateNewFileTest {
  private CreateNewFile testCreateFile;
  private int maxTasks;
  private int maxEvents;
  private String password;
  private Week exampleWeek;
  private final Task exampleTaskSunday = new Task("this is a task",
      DayOfWeek.SUNDAY, "this must be completed");
  private final Task exampleTaskMonday = new Task("this is a task",
      DayOfWeek.MONDAY, "this must be completed");
  private final Task exampleTaskTuesday = new Task("this is a task",
      DayOfWeek.TUESDAY, "this must be completed");
  private final Task exampleTaskWed = new Task("this is a task",
      DayOfWeek.WEDNESDAY, "this must be completed");
  private final Task exampleTaskThu = new Task("this is a task",
      DayOfWeek.THURSDAY, "this must be completed");
  private final Task exampleTaskFri = new Task("this is a task",
      DayOfWeek.FRIDAY, "this must be completed");
  private final Task exampleTaskSat = new Task("this is a task",
      DayOfWeek.SATURDAY, "this must be completed");

  private final Event exampleEventSunday = new Event("this is an event", DayOfWeek.SUNDAY,
      "this must be completed", 0, 20);
  private final Event exampleEventMonday = new Event("this is an event", DayOfWeek.MONDAY,
      "this must be completed", 0, 20);
  private final Event exampleEventTuesday = new Event("this is an event", DayOfWeek.TUESDAY,
      "this must be completed", 0, 20);
  private final Event exampleEventWed = new Event("this is an event", DayOfWeek.WEDNESDAY,
      "this must be completed", 0, 20);
  private final Event exampleEventThu = new Event("this is an event", DayOfWeek.THURSDAY,
      "this must be completed", 0, 20);
  private final Event exampleEventFri = new Event("this is an event", DayOfWeek.FRIDAY,
      "this must be completed", 0, 20);
  private final Event exampleEventSat = new Event("this is an event", DayOfWeek.SATURDAY,
      "this must be completed", 0, 20);

  private StringBuilder expectedOutputBuilder;

  /**
   * Called before each test method that initializes objects used during testing.
   */
  @BeforeEach
  public void setup() {
    this.testCreateFile = new CreateNewFile();
    this.maxTasks = 3;
    this.maxEvents = 5;
    this.password = "jujuOnThatBeat";
    this.exampleWeek = new Week(this.maxTasks, this.maxEvents,
        "createFileTestOutput.bujo", this.password);
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

    JsonBujoFile jsonBujoFile = new JsonBujoFile(maxTasks, maxEvents,
        password, exampleWeek.weekToJson());
    JsonNode nodeBujo = JsonUtils.serializeRecord(jsonBujoFile);
    String expecOut = nodeBujo.toPrettyString();
    expectedOutputBuilder = new StringBuilder(expecOut);
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

      // tests that the expected outputs are displayed.
      BufferedReader expectedOutput =
          new BufferedReader(new FileReader("src/test/testfiles/createFileTestOutput.bujo"));
      BufferedReader actualOutput = new BufferedReader(
          new StringReader(expectedOutputBuilder.toString()));
      
      while (expectedOutput.ready() && actualOutput.ready()) {
        assertEquals(expectedOutput.readLine(), actualOutput.readLine());
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}