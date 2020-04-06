package mcorp.domain.openweather;

public record Wind(
        Long speed,
        Long deg) {

    public Wind {
    }

    public Wind() {
        this(null, null);
    }
}
