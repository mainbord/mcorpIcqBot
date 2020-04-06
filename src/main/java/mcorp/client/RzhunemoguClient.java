package mcorp.client;

import mcorp.domain.rzhunemogu.RzhunemoguRandomRequestType;

public interface RzhunemoguClient {

    String getRandomAnekdotJoke(RzhunemoguRandomRequestType cType);
}
