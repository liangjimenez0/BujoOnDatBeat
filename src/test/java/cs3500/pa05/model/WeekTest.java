package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  Week thirdWeek;

  /**
   * Initializes objects before each test
   */
  @BeforeEach
  public void setup() {
    this.firstWeek = new Week(3, 4, "week1", "badPassword");
    this.secondWeek = new Week(0, 0, "week2", "betterPassword");
    this.thirdWeek = new Week(15, 22, "week3", "worsePassword");
    this.thirdWeek.getDays().get(0).getTasks().add(
        new Task("shopping", DayOfWeek.FRIDAY, "shopping"));
    this.thirdWeek.getDays().get(0).getEvents().add(
        new Event("bday", DayOfWeek.SUNDAY, "surprise party", 1000L, 120));
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
   * Tests that a week returns the correct day
   */
  @Test
  void getDay() {
    assertEquals(new Day(DayOfWeek.MONDAY), firstWeek.getDay(DayOfWeek.MONDAY));
    assertEquals(new Day(DayOfWeek.TUESDAY), firstWeek.getDay(DayOfWeek.TUESDAY));
  }

  /**
   * Tests that a string given is a valid day
   */
  @Test
  void checkDay() {
    assertTrue(firstWeek.checkDay("TUESDAY"));
    assertTrue(firstWeek.checkDay("WEDNESDAY"));
    assertTrue(firstWeek.checkDay("FRIDAY"));
    assertTrue(firstWeek.checkDay("MONDAY"));
    assertTrue(firstWeek.checkDay("THURSDAY"));
    assertTrue(firstWeek.checkDay("SATURDAY"));
    assertTrue(firstWeek.checkDay("SUNDAY"));
    assertFalse(firstWeek.checkDay("NOT A DAY"));
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

  /**
   * Tests that start days are correctly initialized by a given day
   */
  @Test
  void initializwWithStartDay() {
    List<Day> weekStartingWithFriday = new ArrayList<>();
    weekStartingWithFriday.add(new Day(DayOfWeek.FRIDAY));
    weekStartingWithFriday.add(new Day(DayOfWeek.SATURDAY));
    weekStartingWithFriday.add(new Day(DayOfWeek.SUNDAY));
    weekStartingWithFriday.add(new Day(DayOfWeek.MONDAY));
    weekStartingWithFriday.add(new Day(DayOfWeek.TUESDAY));
    weekStartingWithFriday.add(new Day(DayOfWeek.WEDNESDAY));
    weekStartingWithFriday.add(new Day(DayOfWeek.THURSDAY));
    List<Day> weekStartingWithWednesday = new ArrayList<>();
    weekStartingWithWednesday.add(new Day(DayOfWeek.WEDNESDAY));
    weekStartingWithWednesday.add(new Day(DayOfWeek.THURSDAY));
    weekStartingWithWednesday.add(new Day(DayOfWeek.FRIDAY));
    weekStartingWithWednesday.add(new Day(DayOfWeek.SATURDAY));
    weekStartingWithWednesday.add(new Day(DayOfWeek.SUNDAY));
    weekStartingWithWednesday.add(new Day(DayOfWeek.MONDAY));
    weekStartingWithWednesday.add(new Day(DayOfWeek.TUESDAY));

    Week fridayStartWeek = new Week(5, 5, "filename", "FRIDAY", "password", "weekname");
    Week wednesdayStartWeek = new Week(5, 5, "filename", "WEDNESDAY", "password", "weekname");

    assertEquals(weekStartingWithFriday.size(), weekStartingWithWednesday.size());
    assertEquals(weekStartingWithWednesday, wednesdayStartWeek.getDays());
    assertEquals(weekStartingWithFriday, fridayStartWeek.getDays());
  }

  /**
   * Tests that the weeks contain the correct amount of tasks.
   */
  @Test
  void getAllTasks() {
    assertEquals(new ArrayList<>(), firstWeek.getAllTasks());
    assertEquals(1, thirdWeek.getAllTasks().size());
    assertEquals(new Task("shopping", DayOfWeek.FRIDAY, "shopping"),
        thirdWeek.getAllTasks().get(0));
  }

  /**
   * Tests that the weeks contain the correct amount of events.
   */
  @Test
  void getAllEvents() {
    assertEquals(new ArrayList<>(), firstWeek.getAllEvents());
    assertEquals(1, thirdWeek.getAllTasks().size());
    assertEquals(new Event("bday", DayOfWeek.SUNDAY, "surprise party", 1000L, 120),
        thirdWeek.getAllEvents().get(0));
  }

  /**
   * Tests that the correct name is given
   */
  @Test
  void getName() {
    assertEquals("week1", firstWeek.getName());
    assertEquals("week2", secondWeek.getName());
    assertEquals("week3", thirdWeek.getName());
  }

  /**
   * Tests that the name for a week can correctly be updated
   */
  @Test
  void setNameForWeek() {
    Week changeNameTest = new Week(2, 2, "weekname", "password");
    changeNameTest.setNameForWeek("newName");
    assertEquals("newName", changeNameTest.getNameForWeek());
  }

  /**
   * Tests that the name is correctly returned
   */
  @Test
  void getNameForWeek() {
    assertEquals("welcome to your bullet journal", firstWeek.getNameForWeek());
    assertEquals("welcome to your bullet journal", secondWeek.getNameForWeek());
  }

  /**
   * Tests that the max events can be correctly updated.
   */
  @Test
  void changeMaxEvents() {
    assertEquals(4, firstWeek.getMaxEvents());
    assertEquals(0, secondWeek.getMaxEvents());

    firstWeek.changeMaxEvents(7);
    secondWeek.changeMaxEvents(4);

    assertEquals(7, firstWeek.getMaxEvents());
    assertEquals(4, secondWeek.getMaxEvents());
  }

  /**
   * Tests that the max tasks can be correctly updated.
   */
  @Test
  void changeMaxTasks() {
    assertEquals(3, firstWeek.getMaxTasks());
    assertEquals(0, secondWeek.getMaxTasks());

    firstWeek.changeMaxTasks(7);
    secondWeek.changeMaxTasks(4);

    assertEquals(7, firstWeek.getMaxTasks());
    assertEquals(4, secondWeek.getMaxTasks());
  }

  /**
   * Tests that the password is correctly returned
   */
  @Test
  void getPassword() {
    assertEquals("badPassword", firstWeek.getPassword());
    assertEquals("betterPassword", secondWeek.getPassword());
  }

  /**
   * Tests that the weekday start is correctly returned
   */
  @Test
  void getWeekdayStart() {
    Week fridayStartWeek = new Week(5, 5, "filename", "MONDAY", "password", "week name");
    assertEquals(DayOfWeek.MONDAY, fridayStartWeek.getWeekdayStart());

    Week thursdayStartWeek = new Week(5, 5, "filename", "THURSDAY", "password", "week name");
    assertEquals(DayOfWeek.THURSDAY, thursdayStartWeek.getWeekdayStart());
  }
}