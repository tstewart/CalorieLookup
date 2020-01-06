package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.error.APICallLimitReachedException;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;

public abstract class Connection {
    private String connectionUrl;

    public abstract String request(APIRequest request) throws InvalidRequestException, APICallLimitReachedException;

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
}
