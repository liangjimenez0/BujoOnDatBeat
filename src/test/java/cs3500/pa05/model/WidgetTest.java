package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Handles testing for widgets
 */
class WidgetTest {
  Widget task;
  Widget event;

  /**
   * Initializes objects for testing
   */
  @BeforeEach
  void setUp() {
    task = new Task("study for math", DayOfWeek.SUNDAY, "math test on monday");
    event = new Event("math test", DayOfWeek.MONDAY, "good luck!", 1000L, 140);
  }

  /**
   * Tests that the correct day is returned.
   */
  @Test
  void getDay() {
    assertEquals(DayOfWeek.SUNDAY, task.getDay());
    assertEquals(DayOfWeek.MONDAY, event.getDay());
  }

  /**
   * Tests that the correct name is returned.
   */
  @Test
  void getName() {
    assertEquals("study for math", task.getName());
    assertEquals("math test", event.getName());
  }

  /**
   * Tests that the correct description is returned
   */
  @Test
  void getDescription() {
    assertEquals("math test on monday", task.getDescription());
    assertEquals("good luck!", event.getDescription());
  }

  /**
   * Tests that the name can be updated correctly
   */
  @Test
  void changeName() {
    assertEquals("study for math", task.getName());
    assertEquals("math test", event.getName());

    task.changeName("laundry");
    event.changeName("OOD lab");

    assertEquals("laundry", task.getName());
    assertEquals("OOD lab", event.getName());
  }

  /**
   * Tests that the day can be changed correctly.
   */
  @Test
  void changeDay() {
    assertEquals(DayOfWeek.SUNDAY, task.getDay());
    assertEquals(DayOfWeek.MONDAY, event.getDay());

    task.changeDay(DayOfWeek.TUESDAY);
    event.changeDay(DayOfWeek.FRIDAY);

    assertEquals(DayOfWeek.TUESDAY, task.getDay());
    assertEquals(DayOfWeek.FRIDAY, event.getDay());
  }

  /**
   * Tests that the description is changed correctly.
   */
  @Test
  void changeDescription() {
    assertEquals("math test on monday", task.getDescription());
    assertEquals("good luck!", event.getDescription());

    task.changeDescription("study with group");
    event.changeDescription("take wellness day");

    assertEquals("study with group", task.getDescription());
    assertEquals("take wellness day", event.getDescription());
  }
}