package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.json.JsonEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the testing for the Event class
 */
class EventTest {
  private Event birthday;
  private Event graduation;
  private Event wedding;

  /**
   * Initializes objects before each test
   */
  @BeforeEach
  public void setup() {
    this.birthday = new Event("birthday", DayOfWeek.FRIDAY, "attend birthday party", 1800L, 180);
    this.graduation = new Event("graduation", DayOfWeek.WEDNESDAY, "grad ceremony", 1000L, 120);
    this.wedding = new Event("wedding", DayOfWeek.SUNDAY, "attend wedding", 2200L, 240);
  }

  /**
   * Represents the testing of turning an event to a JsonEvent
   */
  @Test
  void eventToJson() {
    JsonEvent jsonBirthday = this.birthday.eventToJson();
    assertEquals(DayOfWeek.FRIDAY, jsonBirthday.day());
    assertEquals("birthday", jsonBirthday.name());
    assertEquals(180, jsonBirthday.duration());
    assertEquals("attend birthday party", jsonBirthday.description());
    assertEquals(1800L, jsonBirthday.startTime());

    JsonEvent jsonGrad = this.graduation.eventToJson();
    assertEquals(DayOfWeek.WEDNESDAY, jsonGrad.day());
    assertEquals("graduation", jsonGrad.name());
    assertEquals(120, jsonGrad.duration());
    assertEquals("grad ceremony", jsonGrad.description());
    assertEquals(1000L, jsonGrad.startTime());

    JsonEvent jsonWedding = this.wedding.eventToJson();
    assertEquals(DayOfWeek.SUNDAY, jsonWedding.day());
    assertEquals("wedding", jsonWedding.name());
    assertEquals(240, jsonWedding.duration());
    assertEquals("attend wedding", jsonWedding.description());
    assertEquals(2200L, jsonWedding.startTime());
  }
}