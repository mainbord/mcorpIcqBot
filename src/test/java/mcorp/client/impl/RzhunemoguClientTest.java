package mcorp.client.impl;

import mcorp.UtilTest;
import mcorp.domain.rzhunemogu.RzhunemoguRandomRequestType;
import mcorp.domain.rzhunemogu.RzhunemoguResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

class RzhunemoguClientTest {

    private RzhunemoguHttpClient rzhunemoguClient = new RzhunemoguHttpClient();

    @Test
    public void getRandomAnekdotJoke() {
        String joke = rzhunemoguClient.getRandomAnekdotJoke(RzhunemoguRandomRequestType.JOKE);
        System.out.println(joke);
    }

    @Test
     public void jaxbTest() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(RzhunemoguResponse.class);
        Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
        RzhunemoguResponse response = (RzhunemoguResponse) jaxbMarshaller.unmarshal(new StringReader(UtilTest.getTestObjectFromResources("RzhunemoguResponse.xml")));
        Assertions.assertNotNull(response.getContent());
        System.out.println(response.getContent());
    }
}