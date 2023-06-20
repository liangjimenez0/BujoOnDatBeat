package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The testing class for reading a Bujo file and extracting information from that file.
 */
class ReadFileTest {
  private ReadFile testRead;
  private int maxTasks;
  private int maxEvents;
  private String password;

  /**
   * Initializes objects before each test
   */
  @BeforeEach
  public void setup() {
    this.maxTasks = 5;
    this.maxEvents = 5;
    this.password = "YURRRRR";
    this.testRead = new ReadFile(new File("src/test/testfiles/readFileTestInput.bujo"));
  }

  /**
   * Represents the testing for processing a Bujo file
   */
  @Test
  void processFile() {
    Week fileData = this.testRead.processFile();
    assertEquals(7, fileData.getDays().size());
    assertEquals(4, fileData.getMaxEvents());
    assertEquals(3, fileData.getMaxTasks());
    assertEquals("YURRRRR", fileData.getPassword());
    assertThrows(RuntimeException.class,
        () -> new ReadFile(new File("fakeFile")));
  }
}