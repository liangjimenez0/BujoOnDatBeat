package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.JsonDay;
import java.util.List;

/**
 * Represents a week record.
 *
 * @param name the user given name
 * @param days are the list of week days that occur in a JsonWeek
 */
public record JsonWeek(
    @JsonProperty("Name") String name,
    @JsonProperty("Days") List<JsonDay> days
) {
}
