package io.github.tstewart.CalorieLookup.error;

/**
 * Author: Thomas Stewart
 * Thrown if the API request is malformed or invalid
 */
public class InvalidRequestException extends Exception {

    public InvalidRequestException(final String errorText) {
        super(errorText);
    }
}
