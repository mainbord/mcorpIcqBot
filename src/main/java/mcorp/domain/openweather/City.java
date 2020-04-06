package mcorp.domain.openweather;

public record City(
        String id,
        String name,
        Coordinate coord,
        String country,
        Long population,
        Long timezone,
        Long sunrise,
        Long sunset) {
    public City {
    }

    public City() {
        this(null, null, null, null, null, null, null, null);
    }

}

