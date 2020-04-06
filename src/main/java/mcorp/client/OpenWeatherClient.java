package mcorp.client;

import mcorp.domain.openweather.OpenWeatherResponse;

public interface OpenWeatherClient {

    OpenWeatherResponse getWeather(int numDays, String city);

}
