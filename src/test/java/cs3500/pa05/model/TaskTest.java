package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing class for a task
 */
class TaskTest {
  private Task laundry;
  private Task callFamily;
  private Task clean;

  /**
   * Initializes objects before each test
   */
  @BeforeEach
  public void setup() {
    this.laundry = new Task("laundry", DayOfWeek.MONDAY, "finish laundry");
    this.callFamily = new Task("call family", DayOfWeek.SUNDAY, "call the fam");
    this.clean = new Task("clean", DayOfWeek.THURSDAY, "clean the house");
  }

  /**
   * Testing turning a task to a JsonTask
   */
  @Test
  void taskToJson() {
    JsonTask jsonLaundry = this.laundry.taskToJson();
    assertEquals(DayOfWeek.MONDAY, jsonLaundry.day());
    assertEquals("laundry", jsonLaundry.name());
    assertEquals("finish laundry", jsonLaundry.description());

    JsonTask jsonCallFam = this.callFamily.taskToJson();
    assertEquals(DayOfWeek.SUNDAY, jsonCallFam.day());
    assertEquals("call family", jsonCallFam.name());
    assertEquals("call the fam", jsonCallFam.description());

    JsonTask jsonClean = this.clean.taskToJson();
    assertEquals(DayOfWeek.THURSDAY, jsonClean.day());
    assertEquals("clean", jsonClean.name());
    assertEquals("clean the house", jsonClean.description());
  }

  /**
   * Tests that tasks return the correct completed boolean
   */
  @Test
  void getCompleted() {
    assertFalse(clean.getCompleted());
    callFamily.changeCompleted(true);
    assertTrue(callFamily.getCompleted());
    assertFalse(laundry.getCompleted());
  }

  /**
   * Tests that tasks can know if they are equal to other objects.
   */
  @Test
  void equals() {
    assertTrue(clean.equals(clean));
    assertFalse(callFamily.equals(clean));
    assertFalse(laundry.equals(21));
    assertFalse(new Task("laundry", DayOfWeek.TUESDAY, "finish laundry").equals(
        new Task("laundry", DayOfWeek.MONDAY, "finish laundry")));
    assertFalse(new Task("monday", DayOfWeek.MONDAY, "finish laundry").equals(
        new Task("laundry", DayOfWeek.MONDAY, "finish laundry")));
    assertFalse(new Task("laundry", DayOfWeek.MONDAY, "laundry").equals(
        new Task("laundry", DayOfWeek.MONDAY, "finish laundry")));
  }

  /**
   * Tests that we can correctly change whether the task was completed
   */
  @Test
  void changeCompleted() {
    clean.changeCompleted(false);
    callFamily.changeCompleted(false);
    laundry.changeCompleted(true);

    assertTrue(laundry.getCompleted());
    assertFalse(clean.getCompleted());
    assertFalse(callFamily.getCompleted());
  }
}