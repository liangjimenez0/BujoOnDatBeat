package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JsonEvent(
    @JsonProperty("Name") String name,
    @JsonProperty ("Description") String description,
    @JsonProperty ("Day of Week") String day,
    @JsonProperty ("Start Time") long startTime,
    @JsonProperty ("Duration") int duration
) {
}
