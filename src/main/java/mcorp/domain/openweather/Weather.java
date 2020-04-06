package mcorp.domain.openweather;

public record Weather(
        Integer id,
        String main,
        String description,
        String icon) {

    public Weather {
    }

    public Weather() {
        this(null, null, null, null);
    }
}
