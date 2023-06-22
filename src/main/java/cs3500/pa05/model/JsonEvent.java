package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayOfWeek;

/**
 * Represents an event record.
 *
 * @param name is the name of this JsonEvent
 * @param description is a short description of the JsonEvent
 * @param day is the day this JsonEvent occurs on
 * @param startTime is the 24-hour number depicting the time this event occurs on
 * @param duration is the length of this event in minutes
 */
public record JsonEvent(
    @JsonProperty("Name") String name,
    @JsonProperty ("Description") String description,
    @JsonProperty ("Day of Week") DayOfWeek day,
    @JsonProperty ("Start Time") long startTime,
    @JsonProperty ("Duration") int duration) {
}
