package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record JsonWeek(
    @JsonProperty("Days") List<JsonDay> days
) {
}
