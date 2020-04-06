package mcorp.domain.openweather;

import java.util.List;

public record OpenWeatherResponse(
        List<Dt>list,
        Integer cod,
        String message,
        Integer cnt,
        City city,
        String country,
        Long population,
        Long timezone,
        Long sunrise,
        Long sunset) {

    public OpenWeatherResponse() {
        this(null, null,null,null,null,null,null,null,null, null);
    }

    public static void main(String[] args) {
        new OpenWeatherResponse();
    }
}
