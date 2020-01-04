package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.error.APICallLimitReachedException;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;

public abstract class Connection {
    String connectionUrl;

    public abstract String request(APIRequest request) throws InvalidRequestException, APICallLimitReachedException;

}
