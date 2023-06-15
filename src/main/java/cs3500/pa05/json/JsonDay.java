package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record JsonDay(
    @JsonProperty ("Name") String name,
    @JsonProperty ("Tasks") List<JsonTask> tasks,
    @JsonProperty ("Events") List<JsonEvent> events
) {
}
