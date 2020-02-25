package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.request.Request;

public class APIRequest {
    /**
     * Food or Recipe request to be formatted and sent to API
     */
    private Request request = null;
    /**
     * Formatted API request string (e.g. www.api.com/q=chicken)
     */
    private String formattedRequest = null;

    public APIRequest(final Request request) {
        super();
        this.request = request;
    }

    public APIRequest() {
        super();

    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(final Request request) {
        this.request = request;
    }

}
