package mcorp;

import mcorp.config.AppConfig;
import mcorp.usecase.IcqEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.im.botapi.BotApiClient;
import ru.mail.im.botapi.BotApiClientController;

public class Application {

    private final static Logger log = LogManager.getLogger("app");
    private final static String token = AppConfig.getProperties().getProperty("token");

    public static void main(String[] args) {
        BotApiClient client = new BotApiClient(token);
        BotApiClientController controller = BotApiClientController.startBot(client);
        client.addOnEventFetchListener(new IcqEventListener(controller));
    }
}
