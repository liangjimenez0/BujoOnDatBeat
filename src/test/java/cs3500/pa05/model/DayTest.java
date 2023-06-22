package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the testing for the Day class.
 */
class DayTest {
  private Day mon;
  private Day tues;
  private Day wed;
  private Day thurs;
  private Day fri;
  private Day sat;
  private Day sun;
  private final List<Task> taskList = new ArrayList<>();
  private Task laundry;
  private Task study;
  private final List<Event> eventList = new ArrayList<>();
  private Event dinner;
  private Event birthday;
  private JsonDay jsonMon;
  private JsonDay jsonTues;
  private JsonDay jsonFri;

  /**
   * Called before each test method that initializes objects used during testing.
   */
  @BeforeEach
  public void setup() {
    this.mon = new Day(DayOfWeek.MONDAY);
    this.tues = new Day(DayOfWeek.TUESDAY);
    this.wed = new Day(DayOfWeek.WEDNESDAY);
    this.thurs = new Day(DayOfWeek.THURSDAY);
    this.fri = new Day(DayOfWeek.FRIDAY);
    this.sat = new Day(DayOfWeek.SATURDAY);
    this.sun = new Day(DayOfWeek.SUNDAY);
    this.laundry = new Task("laundry", DayOfWeek.MONDAY, "finish laundry");
    this.laundry.changeCompleted(true);
    this.study = new Task("study", DayOfWeek.TUESDAY, "study for exam");
    this.dinner = new Event("dinner", DayOfWeek.FRIDAY, "dinner with fam", 1800L, 120);
    this.birthday = new Event("birthday", DayOfWeek.SATURDAY, "mom's birthday party", 1200L, 60);
    this.mon.addToTask(this.laundry);
    this.tues.addToTask(this.study);
    this.fri.addToEvent(this.dinner);
    this.sat.addToEvent(this.birthday);
    this.jsonMon = this.mon.dayToJson();
    this.jsonTues = this.tues.dayToJson();
    this.jsonFri = this.fri.dayToJson();
  }

  /**
   * Testing the method getDayOfWeek that returns the Enum of the weekday
   */
  @Test
  void getDayOfWeek() {
    assertEquals(DayOfWeek.MONDAY, this.mon.getDayOfWeek());
    assertEquals(DayOfWeek.TUESDAY, this.tues.getDayOfWeek());
    assertEquals(DayOfWeek.WEDNESDAY, this.wed.getDayOfWeek());
    assertEquals(DayOfWeek.THURSDAY, this.thurs.getDayOfWeek());
    assertEquals(DayOfWeek.FRIDAY, this.fri.getDayOfWeek());
    assertEquals(DayOfWeek.SATURDAY, this.sat.getDayOfWeek());
    assertEquals(DayOfWeek.SUNDAY, this.sun.getDayOfWeek());
  }

  /**
   * Testing the method that returns a day's list of tasks
   */
  @Test
  void getTasks() {
    this.taskList.add(this.laundry);
    assertEquals(this.taskList, this.mon.getTasks());
    this.taskList.clear();
    this.taskList.add(this.study);
    assertEquals(this.taskList, this.tues.getTasks());
  }

  /**
   * Testing the method that adds a given task to this Day's list of tasks
   */
  @Test
  void addToTask() {
    assertTrue(this.mon.getTasks().contains(this.laundry));
    assertTrue(this.tues.getTasks().contains(this.study));
    this.wed.addToTask(null);
    assertTrue(this.wed.getTasks().contains(null));
  }

  /**
   * Testing the method that adds a given Event to this Day's list of events
   */
  @Test
  void addToEvent() {
    assertTrue(this.fri.getEvents().contains(this.dinner));
    assertTrue(this.sat.getEvents().contains(this.birthday));
    this.sun.addToEvent(null);
    assertTrue(this.sun.getEvents().contains(null));
  }

  /**
   * Testing the method that returns this Day's list of events
   */
  @Test
  void getEvents() {
    this.eventList.add(this.dinner);
    assertEquals(this.eventList, this.fri.getEvents());
    this.eventList.clear();
    this.eventList.add(this.birthday);
    assertEquals(this.eventList, this.sat.getEvents());
  }

  /**
   * Testing the method that turns a Day's information to a JsonDay record
   */
  @Test
  void dayToJson() {
    assertEquals("MONDAY", this.jsonMon.name());
    assertTrue(this.jsonMon.tasks().contains(new JsonTask(
        "laundry", "finish laundry", DayOfWeek.MONDAY, true)));
    assertEquals("TUESDAY", this.jsonTues.name());
    assertTrue(this.jsonTues.tasks().contains(new JsonTask(
        "study", "study for exam", DayOfWeek.TUESDAY, false)));
    assertEquals("FRIDAY", this.jsonFri.name());
    assertTrue(this.jsonFri.events().contains(
        new JsonEvent("dinner", "dinner with fam", DayOfWeek.FRIDAY, 1800L, 120)));
  }

  /**
   * Tests that the day's task completion percentage is correct
   */
  @Test
  void taskCompletionPercentage() {
    assertEquals(1.0, mon.taskCompletionPercentage());
    assertEquals(0.0, tues.taskCompletionPercentage());
    assertEquals(0.0, wed.taskCompletionPercentage());
    assertEquals(0.0, thurs.taskCompletionPercentage());
    fri.addToTask(laundry);
    assertEquals(1.0, fri.taskCompletionPercentage());
    sat.addToTask(laundry);
    sat.addToTask(study);
    assertEquals(0.5, sat.taskCompletionPercentage());
    assertEquals(0.0, sun.taskCompletionPercentage());
  }

  /**
   * Tests that the day holds the correct number of completed tasks
   */
  @Test
  void getNumOfCompletedTasks() {
    fri.addToTask(laundry);
    fri.addToTask(laundry);
    sat.addToTask(laundry);
    sat.addToTask(study);
    assertEquals(1, mon.getNumOfCompletedTasks());
    assertEquals(0, tues.getNumOfCompletedTasks());
    assertEquals(0, wed.getNumOfCompletedTasks());
    assertEquals(0, thurs.getNumOfCompletedTasks());
    assertEquals(2, fri.getNumOfCompletedTasks());
    assertEquals(1, sat.getNumOfCompletedTasks());
    assertEquals(0, sun.getNumOfCompletedTasks());
  }

  /**
   * Tests that days determine equality between other objects correctly.
   */
  @Test
  void equals() {
    assertTrue(mon.equals(mon));
    assertFalse(mon.equals(tues));
    assertFalse(fri.equals(7));
  }
}