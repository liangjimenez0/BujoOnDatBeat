package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayOfWeek;

public record JsonTask(
    @JsonProperty ("Name") String name,
    @JsonProperty ("Description") String description,
    @JsonProperty ("Day of Week") DayOfWeek day,
    @JsonProperty ("Completed") boolean completed) {
}
