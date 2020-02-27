package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.error.InvalidRequestException;
import io.github.tstewart.CalorieLookup.error.InvalidResponseException;
import org.json.JSONObject;

/**
 * Author: Thomas Stewart
 */
public abstract class Connection {
    /**
     * API connection string (e.g. www.api.com)
     */
    private String connectionUrl = null;

    /**
     * 1. Sends a HTML request to the Edamam API with the provided API key, ID, and formatted API request
     *
     * @param request Formatted API request
     * @return Returns the HTML response from Edamam
     * @throws InvalidRequestException If the request was malformed (e.g. contained invalid information)
     */
    public abstract JSONObject request(APIRequest request) throws InvalidRequestException, InvalidResponseException;

    public String getConnectionUrl() {
        return this.connectionUrl;
    }

    public void setConnectionUrl(final String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
}
