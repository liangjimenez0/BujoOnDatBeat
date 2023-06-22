package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a day record.
 *
 * @param name is the week day name
 * @param tasks are the list of tasks that occur on this JsonDay
 * @param events are the list of events that occur on this JsonDay
 */
public record JsonDay(
    @JsonProperty ("Name") String name,
    @JsonProperty ("Tasks") List<JsonTask> tasks,
    @JsonProperty ("Events") List<JsonEvent> events) {}
