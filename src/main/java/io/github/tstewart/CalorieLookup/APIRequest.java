package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.request.Request;

public class APIRequest {
    private Request request;
    private String formattedRequest;

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
