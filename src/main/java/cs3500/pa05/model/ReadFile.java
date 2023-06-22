package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Represents the class that reads and interprets a Bujo file
 */
public class ReadFile {
  private final File file;
  private Week week;
  private final ObjectMapper mapper = new ObjectMapper();
  private final JsonNode node;
  private final JsonBujoFile jsonBujoFile;

  /**
   * Initializes an object that can read a given file
   *
   * @param file is the given file that is to be scanned and read from
   */
  public ReadFile(File file) {
    this.file = file;

    try {
      this.node = mapper.readTree(this.file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    this.jsonBujoFile = mapper.convertValue(this.node, JsonBujoFile.class);
  }

  /**
   * Processes the given file to a bujo
   *
   * @return a week with the data pulled from file object in this class
   */
  public Week processFile() {
    createWeek();
    setWidgets();

    return this.week;
  }

  /**
   * Adds the max number of tasks and events to this week's fields from the JsonBujo data
   */
  private void createWeek() {
    int maxTasksNum = jsonBujoFile.maxTasks();
    int maxEventsNum = jsonBujoFile.maxEvents();

    this.week = new Week(maxTasksNum, maxEventsNum, file.getName(),
        jsonBujoFile.week().days().get(0).name(), jsonBujoFile.password(),
        jsonBujoFile.week().name());
    this.week.setNameForWeek(jsonBujoFile.week().name());
  }

  /**
   * Adds the events and tasks of this week's happenings from the JsonBujo file data
   */
  private void setWidgets() {
    JsonWeek jsonWeek = jsonBujoFile.week();
    List<JsonDay> jsonDayList = jsonWeek.days();

    for (int i = 0; i < jsonDayList.size(); i++) {
      initializeDayTasks(jsonDayList.get(i), this.week.getDays().get(i));
      initializeDayEvents(jsonDayList.get(i), this.week.getDays().get(i));
    }
  }

  /**
   * Initializes the tasks from the file for a day.
   *
   * @param jsonDay is the given JsonDay
   * @param day     is the week day that these tasks occur on
   */
  private void initializeDayTasks(JsonDay jsonDay, Day day) {
    for (JsonTask t : jsonDay.tasks()) {
      String name = t.name();
      String description = t.description();
      DayOfWeek dayOfWeek = t.day();
      boolean completedBoolean = t.completed();

      Task task = new Task(name, dayOfWeek, description, completedBoolean);

      day.getTasks().add(task);
    }
  }

  /**
   * Initializes the events from the file for a day.
   *
   * @param jsonDay is the given JsonDay
   * @param day     is the week day that these events occur on
   */
  private void initializeDayEvents(JsonDay jsonDay, Day day) {
    for (JsonEvent e : jsonDay.events()) {
      String name = e.name();
      String description = e.description();
      DayOfWeek dayOfWeek = e.day();
      long startTimeLong = e.startTime();
      int durationInt = e.duration();

      Event event = new Event(name, dayOfWeek, description, startTimeLong, durationInt);

      day.getEvents().add(event);
    }
  }
}

