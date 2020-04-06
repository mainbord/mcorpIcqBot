package mcorp.client;

import mcorp.domain.forismatic.ForismaticResponse;

import java.util.Map;

public interface ForismaticClient {

    ForismaticResponse getRandomQuotation();
}
