package mcorp;

import mcorp.config.AppConfig;
import mcorp.usecase.IcqEventListener;
import ru.mail.im.botapi.BotApiClient;
import ru.mail.im.botapi.BotApiClientController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Application {

    private final static Logger log = LogManager.getLogger("app");
    private final static String token = AppConfig.getProperties().getProperty("token");

    public static void main(String[] args) throws IOException {
        BotApiClient client = new BotApiClient(token);
        BotApiClientController controller = BotApiClientController.startBot(client);
        client.addOnEventFetchListener(new IcqEventListener(controller));
    }
}
