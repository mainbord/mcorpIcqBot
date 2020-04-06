package mcorp.client.impl;

import mcorp.client.RzhunemoguClient;
import mcorp.config.AppConfig;
import mcorp.domain.rzhunemogu.RzhunemoguRandomRequestType;
import mcorp.domain.rzhunemogu.RzhunemoguResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Executors;

import static java.util.Optional.ofNullable;

public class RzhunemoguHttpClient implements RzhunemoguClient {

    private final static Logger log = LogManager.getLogger("app");
    private final HttpClient httpClient;
    private final String rzhunemoguEndpoint = AppConfig.getProperties().getProperty("client.rzhunemogu.url");
    private final Integer connectTimeoutInMS = 7000;
    private final Integer readTimeoutInMs = 7000;

    public RzhunemoguHttpClient() {
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(connectTimeoutInMS))
                .executor(Executors.newCachedThreadPool())
                .build();
    }

    @Override
    public String getRandomAnekdotJoke(RzhunemoguRandomRequestType cType) {
        try {
            String uri = String.format("%s/Rand.aspx?%s=%s",
                    rzhunemoguEndpoint,
                    "CType", String.valueOf(cType.getcType()));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();
            try {
                HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
                JAXBContext jaxbContext = JAXBContext.newInstance(RzhunemoguResponse.class);
                Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
                String result = new String(response.body(), "windows-1251");
                return ofNullable(((RzhunemoguResponse) jaxbMarshaller.unmarshal(new StringReader(result))))
                        .orElse(new RzhunemoguResponse("")).getContent();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Error";
        }
    }

}
