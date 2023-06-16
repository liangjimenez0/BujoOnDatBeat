package cs3500.pa05.model;

import cs3500.pa05.json.JsonDay;
import cs3500.pa05.json.JsonEvent;
import cs3500.pa05.json.JsonTask;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day {

  DayOfWeek day;
  List<Task> tasks;
  List<Event> events;

  public Day(DayOfWeek day) {
    this.day = day;
    this.tasks = new ArrayList<>();
    this.events = new ArrayList<>();
  }


  public DayOfWeek getDayOfWeek() {
    return this.day;
  }

  public List<Task> getTasks() {
    return this.tasks;
  }

  public List<Event> getEvents() {
    return this.events;
  }

  public JsonDay dayToJson() {
    List<JsonTask> jsonTasks = new ArrayList<>();
    List<JsonEvent> jsonEvents = new ArrayList<>();

    for (Task t : this.tasks) {
      JsonTask jsonTask = t.taskToJson();
      jsonTasks.add(jsonTask);
    }

    for (Event e : this.events) {
      JsonEvent jsonEvent = e.eventToJson();
      jsonEvents.add(jsonEvent);
    }

    return new JsonDay(this.day.name(), jsonTasks, jsonEvents);
  }
}
