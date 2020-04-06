package mcorp.domain.openweather;

public record Coordinate(
        Long lat,
        Long lon) {

    public Coordinate {
    }

    public Coordinate() {
        this(null, null);
    }
}
