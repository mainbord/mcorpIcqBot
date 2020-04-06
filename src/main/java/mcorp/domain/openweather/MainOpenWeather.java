package mcorp.domain.openweather;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainOpenWeather {
    Long temp;
    Long temp_min;
    Long temp_max;
    Long pressure;
    Long sea_level;
    Long grnd_level;
    Long humidity;
    Long temp_kf;
}
