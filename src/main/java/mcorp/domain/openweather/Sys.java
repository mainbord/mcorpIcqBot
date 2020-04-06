package mcorp.domain.openweather;

public record Sys(
        String pod) {

    public Sys {
    }

    public Sys() {
        this(null);
    }
}
