package mcorp.domain.openweather;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dt {
    String dt;
    MainOpenWeather main;
    List<Weather> weather;
    Cloud clouds;
    Wind wind;
    Sys sys;
    String dt_txt;
    Rain rain;
}
