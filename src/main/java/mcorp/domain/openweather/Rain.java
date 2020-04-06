package mcorp.domain.openweather;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Rain(
        @JsonAlias(value = "3h")
        String hhh) {

    public Rain {
    }

    public Rain() {
        this(null);
    }
}
