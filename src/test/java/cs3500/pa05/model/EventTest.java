package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    assertFalse(new Event("birthday", DayOfWeek.FRIDAY, "attend birthday party", 1800L, 180).equals(
        new Event("funeral", DayOfWeek.FRIDAY, "attend birthday party", 1800L, 180)));
    assertFalse(new Event("birthday", DayOfWeek.FRIDAY, "attend birthday party", 1800L, 180).equals(
        new Event("birthday", DayOfWeek.MONDAY, "attend birthday party", 1800L, 180)));
    assertFalse(new Event("birthday", DayOfWeek.FRIDAY, "birthday party", 1800L, 180).equals(
        new Event("birthday", DayOfWeek.FRIDAY, "attend birthday party", 1800L, 180)));
    assertFalse(new Event("birthday", DayOfWeek.FRIDAY, "attend birthday party", 100L, 180).equals(
        new Event("birthday", DayOfWeek.FRIDAY, "attend birthday party", 1800L, 180)));
    assertFalse(new Event("birthday", DayOfWeek.FRIDAY, "attend birthday party", 1800L, 180).equals(
        new Event("birthday", DayOfWeek.FRIDAY, "attend birthday party", 1800L, 10)));
  }

  /**
   * Tests that events can correctly determine their start time
   */
  @Test
  void getStartTime() {
    assertEquals(2200, wedding.getStartTime());
    assertEquals(1000, graduation.getStartTime());
    assertEquals(1800, birthday.getStartTime());
  }

  /**
   * Tests that events can correctly determine their duration
   */
  @Test
  void getDuration() {
    assertEquals(240, wedding.getDuration());
    assertEquals(120, graduation.getDuration());
    assertEquals(180, birthday.getDuration());
  }

  /**
   * Tests that events can correctly change their start time
   */
  @Test
  void changeStartTime() {
    assertEquals(2200, wedding.getStartTime());
    assertEquals(1000, graduation.getStartTime());
    assertEquals(1800, birthday.getStartTime());
    wedding.changeStartTime(13L);
    graduation.changeStartTime(4L);
    birthday.changeStartTime(7L);
    assertEquals(13, wedding.getStartTime());
    assertEquals(4, graduation.getStartTime());
    assertEquals(7, birthday.getStartTime());
  }

  /**
   * Tests that events can correctly change their duration
   */
  @Test
  void changeDuration() {
    assertEquals(240, wedding.getDuration());
    assertEquals(120, graduation.getDuration());
    assertEquals(180, birthday.getDuration());

    wedding.changeDuration(5);
    graduation.changeDuration(70);
    birthday.changeDuration(3);

    assertEquals(5, wedding.getDuration());
    assertEquals(70, graduation.getDuration());
    assertEquals(3, birthday.getDuration());
  }
}