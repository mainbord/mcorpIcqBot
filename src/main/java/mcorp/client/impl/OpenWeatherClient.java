package mcorp.client.impl;

import feign.Feign;
import feign.Request;
import feign.jackson.JacksonDecoder;
import mcorp.client.OpenWeatherFeignClient;
import mcorp.config.AppConfig;
import mcorp.domain.openweather.OpenWeatherResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class OpenWeatherClient implements OpenWeatherFeignClient {

    private final static Logger log = LogManager.getLogger("app");

    final String APP_ID = AppConfig.getProperties().getProperty("client.open_weather.appid");
    final OpenWeatherFeignClient feignClient;
    final String openWeatherEndpoint = AppConfig.getProperties().getProperty("client.open_weather.url");

    final String QUERY_PARAM = "q";
    final String LAT_PARAM = "lat";
    final String LON_PARAM = "lon";
    final String FORMAT_PARAM = "mode";
    final String UNITS_PARAM = "units";
    final String DAYS_PARAM = "cnt";

    private final Integer connectTimeoutInMS = 7000;
    private final Integer readTimeoutInMs = 7000;

    public OpenWeatherClient() {
        this.feignClient = Feign.builder()
                .logger(new feign.Logger() {
                    @Override
                    protected void log(String configKey, String format, Object... args) {
                        log.info(String.format(methodTag(configKey) + format, args));
                    }
                })
                .logLevel(feign.Logger.Level.FULL)
                .decoder(new JacksonDecoder())
                .options(new Request.Options(connectTimeoutInMS, readTimeoutInMs))
                .target(OpenWeatherFeignClient.class, openWeatherEndpoint);
    }

    public OpenWeatherResponse getWeather(int numDays, String city) {
        Map<String, String> params = new HashMap<>();
        params.put(QUERY_PARAM, city);
        params.put(FORMAT_PARAM, "json");
        params.put(UNITS_PARAM, "metric");
        params.put(DAYS_PARAM, String.valueOf(numDays));
        params.put("appid", APP_ID);
        return feignClient.getWeather(params);
    }

    @Override
    public OpenWeatherResponse getWeather(Map<String, String> params) {
        return null;
    }
}
