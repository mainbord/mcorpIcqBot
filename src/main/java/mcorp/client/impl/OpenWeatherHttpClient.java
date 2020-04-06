package mcorp.client.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import mcorp.client.OpenWeatherClient;
import mcorp.config.AppConfig;
import mcorp.domain.openweather.OpenWeatherResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Executors;

public class OpenWeatherHttpClient implements OpenWeatherClient {

    private final static Logger log = LogManager.getLogger("app");

    private final String APP_ID = AppConfig.getProperties().getProperty("client.open_weather.appid");
    private final HttpClient httpClient;
    final String openWeatherEndpoint = AppConfig.getProperties().getProperty("client.open_weather.url");

    private final String QUERY_PARAM = "q";
    private final String LAT_PARAM = "lat";
    private final String LON_PARAM = "lon";
    private final String FORMAT_PARAM = "mode";
    private final String UNITS_PARAM = "units";
    private final String DAYS_PARAM = "cnt";

    private final Integer connectTimeoutInMS = 7000;
    private final Integer readTimeoutInMs = 7000;

    public OpenWeatherHttpClient() {
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(connectTimeoutInMS))
                .executor(Executors.newCachedThreadPool())
                .build();
    }

    @Override
    public OpenWeatherResponse getWeather(int numDays, String city) {
        String uri = String.format("%s/data/2.5/forecast?%s=%s&%s=%s&%s=%s&%s=%s&%s=%s",
                openWeatherEndpoint,
                QUERY_PARAM, city,
                FORMAT_PARAM, "json",
                UNITS_PARAM, "metric",
                DAYS_PARAM, String.valueOf(numDays),
                "appid", APP_ID);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(response.body(), OpenWeatherResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
