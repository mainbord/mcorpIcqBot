package mcorp.domain.openweather;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenWeatherResponse {
    List<Dt> list;
    Integer cod;
    String message;
    Integer cnt;
    City city;
    String country;
    Long population;
    Long timezone;
    Long sunrise;
    Long sunset;
}
