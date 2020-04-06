package mcorp.domain.openweather;

public record MainOpenWeather(
        Long temp,
        Long temp_min,
        Long temp_max,
        Long pressure,
        Long sea_level,
        Long grnd_level,
        Long humidity,
        Long temp_kf) {

    public MainOpenWeather {
    }

    public MainOpenWeather() {
        this(null, null, null, null, null, null, null, null);
    }
}
