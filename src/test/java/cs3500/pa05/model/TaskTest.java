package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.json.JsonTask;
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
    assertEquals("finish laundry", jsonLaundry.name());
    assertEquals("laundry", jsonLaundry.description());

    JsonTask jsonCallFam = this.callFamily.taskToJson();
    assertEquals(DayOfWeek.SUNDAY, jsonCallFam.day());
    assertEquals("call the fam", jsonCallFam.name());
    assertEquals("call family", jsonCallFam.description());

    JsonTask jsonClean = this.clean.taskToJson();
    assertEquals(DayOfWeek.THURSDAY, jsonClean.day());
    assertEquals("clean the house", jsonClean.name());
    assertEquals("clean", jsonClean.description());
  }
}