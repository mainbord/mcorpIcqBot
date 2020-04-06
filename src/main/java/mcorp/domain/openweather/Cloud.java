package mcorp.domain.openweather;

public record Cloud(
        Integer all) {
    public Cloud {
    }

    public Cloud() {
        this(null);
    }
}

