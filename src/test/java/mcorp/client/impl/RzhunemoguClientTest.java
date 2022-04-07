package mcorp.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.annotations.SerializedName;
import mcorp.UtilTest;
import mcorp.domain.rzhunemogu.RzhunemoguResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

class RzhunemoguClientTest {

    @Test
    public void getRandomAnekdotJoke() {
    }

    @Test
    public void jaxbTest() throws IOException {

        JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(xmlModule);

        StringReader stringReader = new StringReader(UtilTest.getTestObjectFromResources("RzhunemoguResponse.xml"));
        RzhunemoguResponse rzhunemoguResponse = xmlMapper.readValue(stringReader, RzhunemoguResponse.class);

        Assertions.assertNotNull(rzhunemoguResponse.getContent());
        System.out.println(rzhunemoguResponse.getContent());
    }
}