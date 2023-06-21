package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  /**
   * Tests that events can determine equality correctly
   */
  @Test
  void equals() {
    assertTrue(wedding.equals(wedding));
    assertFalse(wedding.equals(graduation));
    assertFalse(wedding.equals(3));

  }

  /**
   * Tests that events can correctly determine their start time
   */
  void getStartTime() {
    assertEquals(10, wedding.getStartTime());
    assertEquals(12, graduation.getStartTime());
    assertEquals(13, birthday.getStartTime());
  }

  /**
   * Tests that events can correctly determine their duration
   */
  void getDuration() {
    assertEquals(10, wedding.getDuration());
    assertEquals(12, graduation.getDuration());
    assertEquals(13, birthday.getDuration());
  }

  /**
   * Tests that events can correctly change their start time
   */
  void changeStartTime() {
    assertEquals(10, wedding.getDuration());
    assertEquals(12, graduation.getDuration());
    assertEquals(13, birthday.getDuration());
    wedding.changeStartTime(13L);
    graduation.changeStartTime(4L);
    birthday.changeStartTime(7L);
    assertEquals(13, wedding.getDuration());
    assertEquals(4, graduation.getDuration());
    assertEquals(7, birthday.getDuration());
  }

  /**
   * Tests that events can correctly change their duration
   */
  void changeDuration() {
    assertEquals(10, wedding.getDuration());
    assertEquals(12, graduation.getDuration());
    assertEquals(13, birthday.getDuration());

    wedding.changeDuration(5);
    graduation.changeDuration(70);
    birthday.changeDuration(3);

    assertEquals(5, wedding.getDuration());
    assertEquals(70, graduation.getDuration());
    assertEquals(3, birthday.getDuration());
  }
}