package mcorp.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {

    @Test
    void testConfiguration() {
        String token = (String) AppConfig.getProperties().get("token");
        assertNotNull(token);
        System.out.println(token);
    }

}