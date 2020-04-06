package mcorp.client;

import feign.QueryMap;
import feign.RequestLine;
import mcorp.domain.openweather.OpenWeatherResponse;

import java.util.Map;

public interface OpenWeatherFeignClient {

    @RequestLine("GET /data/2.5/forecast")
    OpenWeatherResponse getWeather(@QueryMap Map<String, String> params);

}
