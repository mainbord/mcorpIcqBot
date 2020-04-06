package mcorp.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppConfig {

    private final static Logger log = LogManager.getLogger("app");

    private final static Properties properties = new Properties();

    static {
        try {
            properties.load(Files.newBufferedReader(Paths.get(
                    System.getProperty("user.home") + File.separator + "IcqBotConfig.properties")));
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
