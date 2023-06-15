package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JsonTask(
    @JsonProperty ("Name") String name,
    @JsonProperty ("Description") String description,
    @JsonProperty ("Day of Week") String day,
    @JsonProperty ("Completed") boolean completed) {
}
