package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @param days are the list of week days that occur in a JsonWeek
 */
public record JsonWeek(
    @JsonProperty("Days") List<JsonDay> days
) {
}
