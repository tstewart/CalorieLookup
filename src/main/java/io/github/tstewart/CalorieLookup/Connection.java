package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.error.APICallLimitReachedException;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;
import java.io.IOException;

import io.github.tstewart.CalorieLookup.error.InvalidResponseException;
import org.json.JSONObject;

public abstract class Connection {
    /**
     * API connection string (e.g. www.api.com)
     */
    private String connectionUrl;

    /**
     * 1. Sends a HTML request to the Edamam API with the provided API key, ID, and formatted API request
     * @param request Formatted API request
     * @return Returns the HTML response from Edamam
     * @throws InvalidRequestException If the request was malformed (e.g. contained invalid information)
     * @throws APICallLimitReachedException If the Edamam API has reached the maximum number of hourly queries
     */
    public abstract JSONObject request(APIRequest request) throws InvalidRequestException, APICallLimitReachedException, InvalidResponseException;

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
}
