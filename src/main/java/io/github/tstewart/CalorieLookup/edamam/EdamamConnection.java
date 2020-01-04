package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.Connection;
import io.github.tstewart.CalorieLookup.error.APICallLimitReachedException;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;

public class EdamamConnection extends Connection {

    String apiKey;
    String apiId;

    public EdamamConnection(String apiKey, String apiId) {
        this.apiKey = apiKey;
        this.apiId = apiId;
    }

    @Override
    public String request(APIRequest request) throws InvalidRequestException, APICallLimitReachedException {
        return null;
    }
}
