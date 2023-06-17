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
  JsonBujoFile testBujo;
  int maxTasks;
  int maxEvents;
  JsonWeek jsonWeek;
  Week week;
  JsonDay jsonDay;
  ArrayList<JsonTask> taskList = new ArrayList<>();
  ArrayList<JsonEvent> eventList = new ArrayList<>();
  ArrayList<JsonDay> jsonDayList = new ArrayList<>();


  /**
   * Called before each test method that initializes objects used during testing.
   */
  @BeforeEach
  public void setup() {
    this.testCreateFile = new CreateNewFile();
    this.maxTasks = 3;
    this.maxEvents = 5;
    this.jsonDay = new JsonDay("Monday", this.taskList, this.eventList);
    this.jsonDayList.add(this.jsonDay);
    this.week = new Week(this.maxTasks, this.maxEvents);
    this.testBujo = new JsonBujoFile(this.maxTasks, this.maxEvents, this.jsonWeek);
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