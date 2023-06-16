package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JsonBujoFile(
    @JsonProperty ("Max Tasks") int maxTasks,
    @JsonProperty ("Max Events") int maxEvents,
    @JsonProperty ("Week") JsonWeek week) {
}
