package mcorp.domain.openweather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties
public class City {
    String id;
    String name;
    Coordinate coord;
    String country;
    Long population;
    Long timezone;
    Long sunrise;
    Long sunset;
}
