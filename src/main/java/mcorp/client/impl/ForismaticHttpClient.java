package mcorp.client.impl;

import mcorp.client.ForismaticClient;
import mcorp.config.AppConfig;
import mcorp.domain.forismatic.ForismaticRequestFormat;
import mcorp.domain.forismatic.ForismaticResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.Executors;

public class ForismaticHttpClient implements ForismaticClient {

    private final static Logger log = LogManager.getLogger("app");
    private final HttpClient httpClient;
    private final String forismaticEndpoint = AppConfig.getProperties().getProperty("client.forismatic.url");
    private final Integer connectTimeoutInMS = 7000;
    private final Integer readTimeoutInMs = 7000;

    private final String METHOD_NAME = "method";
    private final String RESPONSE_FORMAT = "format";
    private final String RESPONSE_LANGUAGE = "lang";

    public ForismaticHttpClient() {
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(connectTimeoutInMS))
                .executor(Executors.newCachedThreadPool())
                .build();
    }

    @Override
    public ForismaticResponse getRandomQuotation() {
        String uri = String.format("%s/api/1.0/?%s=%s&%s=%s&%s=%s&%s=%s",
                forismaticEndpoint,
                METHOD_NAME, "getQuote",
                "key", "999999",
                RESPONSE_FORMAT, ForismaticRequestFormat.json.toString(),
                RESPONSE_LANGUAGE, "ru");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return new ForismaticResponse(response.body(), null, null, null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
