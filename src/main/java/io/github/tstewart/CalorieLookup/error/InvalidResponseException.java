package io.github.tstewart.CalorieLookup.error;

/**
 * Author: Thomas Stewart
 * Thrown if the response from the API is invalid or malformed
 */
public class InvalidResponseException extends Exception {
    public InvalidResponseException(final String errorCode) {
        super(errorCode);
    }
}
