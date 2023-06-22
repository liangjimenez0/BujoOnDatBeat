package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayOfWeek;

/**
 * Represents a task record.
 *
 * @param name is the name of this JsonTask
 * @param description is a short description of this JsonTask
 * @param day is the week day that this task occurs on
 * @param completed is a boolean value that is true when this task is completed
 */
public record JsonTask(
    @JsonProperty ("Name") String name,
    @JsonProperty ("Description") String description,
    @JsonProperty ("Day of Week") DayOfWeek day,
    @JsonProperty ("Completed") boolean completed) {
}
