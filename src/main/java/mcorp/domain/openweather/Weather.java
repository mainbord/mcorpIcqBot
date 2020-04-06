package mcorp.domain.openweather;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Weather {
    Integer id;
    String main;
    String description;
    String icon;
}
