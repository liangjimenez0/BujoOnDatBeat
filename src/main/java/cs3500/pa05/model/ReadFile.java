package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.json.JsonBujoFile;
import cs3500.pa05.json.JsonDay;
import cs3500.pa05.json.JsonEvent;
import cs3500.pa05.json.JsonTask;
import cs3500.pa05.json.JsonWeek;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadFile {

  private File file;
  private Week week;
  private ObjectMapper mapper = new ObjectMapper();
  private JsonNode node;
  private JsonBujoFile jsonBujoFile;

  public ReadFile(File file) {
    this.file = file;

    try {
     this.node = mapper.readTree(this.file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    this.jsonBujoFile = mapper.convertValue(this.node, JsonBujoFile.class);
  }

  public Week processFile() {
    createWeek();
    setWidgets();

    return this.week;
  }

  private void createWeek() {
    int maxTasksNum = jsonBujoFile.maxTasks();
    int maxEventsNum = jsonBujoFile.maxEvents();

    this.week = new Week(maxTasksNum, maxEventsNum);
  }

  private void setWidgets() {
    JsonWeek jsonWeek = jsonBujoFile.week();
    List<JsonDay> jsonDayList = jsonWeek.days();

    for (int i = 0; i < jsonDayList.size(); i++) {
      initializeDayTasks(jsonDayList.get(i), this.week.getDays().get(i));
      initializeDayEvents(jsonDayList.get(i), this.week.getDays().get(i));
    }
  }

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

