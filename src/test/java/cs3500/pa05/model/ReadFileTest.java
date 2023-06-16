package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadFileTest {
  ReadFile testRead;
  Week week;
  List<Day> allDays;
  List<Task> allTasks;
  List<Event> allEvents;
  int maxTasks;
  int maxEvents;

  @BeforeEach
  public void setup() {
      this.allDays = new ArrayList<>();
      this.allTasks = new ArrayList<>();
      this.allEvents = new ArrayList<>();
      this.maxTasks = 5;
      this.maxEvents = 5;
  this.week = new Week(this.maxTasks, this.maxEvents);
  this.testRead = new ReadFile(new File("src/test/testfiles/example.bujo"));
  }

  @Test
  void processFile() {
    Week fileData = this.testRead.processFile();
    assertEquals(7, fileData.getDays().size());
    assertEquals(4, fileData.getMaxEvents());
    assertEquals(3, fileData.getMaxTasks());
  }
}