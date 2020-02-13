package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.request.Request;

public class APIRequest {
    /**
     * Food or Recipe request to be formatted and sent to API
     */
    private Request request;
    /**
     * Formatted API request string (e.g. www.api.com/q=chicken)
     */
    private String formattedRequest;

    public APIRequest(Request request) {
        this.request = request;
    }

    public APIRequest() {

    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getFormattedRequest() {
        return formattedRequest;
    }

    public void setFormattedRequest(String formattedRequest) {
        this.formattedRequest = formattedRequest;
    }
}
