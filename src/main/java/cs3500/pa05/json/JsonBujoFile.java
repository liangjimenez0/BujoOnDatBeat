package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @param maxTasks is the maximum number of tasks a user can have in a Bujo file.
 * @param maxEvents is the maximum number of events a user can have in a Bujo file.
 * @param week is the Json interpretation of a week
 */
public record JsonBujoFile(
    @JsonProperty ("Max Tasks") int maxTasks,
    @JsonProperty ("Max Events") int maxEvents,
    @JsonProperty ("Week") JsonWeek week) {
}
