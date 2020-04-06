package mcorp.domain.openweather;

import java.util.List;

public record Dt(
        String dt,
        MainOpenWeather main,
        List<Weather>weather,
        Cloud clouds,
        Wind wind,
        Sys sys,
        String dt_txt,
        Rain rain) {

    public Dt {
    }

    public Dt() {
        this(null, null, null, null, null, null, null, null);
    }
}
