package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.json.JsonDay;
import cs3500.pa05.json.JsonEvent;
import cs3500.pa05.json.JsonTask;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the testing for the Day class.
 */
class DayTest {
  Day mon;
  Day tues;
  Day wed;
  Day thurs;
  Day fri;
  Day sat;
  Day sun;
  List<Task> taskList = new ArrayList<>();
  Task laundry;
  Task study;
  List<Event> eventList = new ArrayList<>();
  Event dinner;
  Event birthday;
  JsonDay jsonMon;
  JsonDay jsonTues;
  JsonDay jsonFri;

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
        "finish laundry", "laundry", DayOfWeek.MONDAY, false)));
    assertEquals("TUESDAY", this.jsonTues.name());
    assertTrue(this.jsonTues.tasks().contains(new JsonTask(
        "study for exam", "study", DayOfWeek.TUESDAY, false)));
    assertEquals("FRIDAY", this.jsonFri.name());

    assertTrue(this.jsonFri.events().contains(
        new JsonEvent("dinner with fam", "dinner", DayOfWeek.FRIDAY, 1800L, 120)));
  }
}