package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.json.JsonDay;
import cs3500.pa05.json.JsonEvent;
import cs3500.pa05.json.JsonTask;
import cs3500.pa05.json.JsonWeek;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the test class for a week
 */
class WeekTest {
  Week firstWeek;
  Week secondWeek;

  /**
   * Initializes objects before each test
   */
  @BeforeEach
  public void setup() {
    this.firstWeek = new Week(3, 4);
    this.secondWeek = new Week(0, 0);
  }

  /**
   * Testing that a week is initialized with the 7 days of the week
   */
  @Test
  void initializeDays() {
    assertEquals(7, this.firstWeek.getDays().size());
    assertSame(this.firstWeek.getDays().get(0).getDayOfWeek(), DayOfWeek.SUNDAY);
    assertSame(this.firstWeek.getDays().get(1).getDayOfWeek(), DayOfWeek.MONDAY);
    assertSame(this.firstWeek.getDays().get(2).getDayOfWeek(), DayOfWeek.TUESDAY);
    assertSame(this.firstWeek.getDays().get(3).getDayOfWeek(), DayOfWeek.WEDNESDAY);
    assertSame(this.firstWeek.getDays().get(4).getDayOfWeek(), DayOfWeek.THURSDAY);
    assertSame(this.firstWeek.getDays().get(5).getDayOfWeek(), DayOfWeek.FRIDAY);
    assertSame(this.firstWeek.getDays().get(6).getDayOfWeek(), DayOfWeek.SATURDAY);
  }

  /**
   * Testing the return of the day
   */
  @Test
  void getDays() {
    assertEquals(DayOfWeek.MONDAY,
        this.secondWeek.getDay(DayOfWeek.MONDAY).getDayOfWeek());
    assertEquals(DayOfWeek.TUESDAY,
        this.secondWeek.getDay(DayOfWeek.TUESDAY).getDayOfWeek());
    assertEquals(DayOfWeek.WEDNESDAY,
        this.secondWeek.getDay(DayOfWeek.WEDNESDAY).getDayOfWeek());
    assertEquals(DayOfWeek.THURSDAY,
        this.secondWeek.getDay(DayOfWeek.THURSDAY).getDayOfWeek());
    assertEquals(DayOfWeek.FRIDAY,
        this.secondWeek.getDay(DayOfWeek.FRIDAY).getDayOfWeek());
    assertEquals(DayOfWeek.SATURDAY,
        this.secondWeek.getDay(DayOfWeek.SATURDAY).getDayOfWeek());
    assertEquals(DayOfWeek.SUNDAY,
        this.secondWeek.getDay(DayOfWeek.SUNDAY).getDayOfWeek());
  }

  /**
   * Testing the maximum number of tasks in this week
   */
  @Test
  void getMaxTasks() {
    assertEquals(3, this.firstWeek.getMaxTasks());
    assertEquals(0, this.secondWeek.getMaxTasks());
  }

  /**
   * Testing the maximum number of events in this week
   */
  @Test
  void getMaxEvents() {
    assertEquals(4, this.firstWeek.getMaxEvents());
    assertEquals(0, this.secondWeek.getMaxEvents());
  }

  /**
   * Testing the transformation of a week to a JsonWeek record
   */
  @Test
  void weekToJson() {
    JsonWeek jsonWeekOne = this.firstWeek.weekToJson();
    List<JsonTask> emptyTasks = new ArrayList<>();
    List<JsonEvent> emptyEvents = new ArrayList<>();
    assertEquals(new JsonDay("SUNDAY", emptyTasks, emptyEvents), jsonWeekOne.days().get(0));
    assertEquals(new JsonDay("MONDAY", emptyTasks, emptyEvents), jsonWeekOne.days().get(1));
    assertEquals(new JsonDay("TUESDAY", emptyTasks, emptyEvents), jsonWeekOne.days().get(2));
    assertEquals(new JsonDay("WEDNESDAY", emptyTasks, emptyEvents), jsonWeekOne.days().get(3));
    assertEquals(new JsonDay("THURSDAY", emptyTasks, emptyEvents), jsonWeekOne.days().get(4));
    assertEquals(new JsonDay("FRIDAY", emptyTasks, emptyEvents), jsonWeekOne.days().get(5));
    assertEquals(new JsonDay("SATURDAY", emptyTasks, emptyEvents), jsonWeekOne.days().get(6));
  }
}