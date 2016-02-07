package com.apiomega.exceptions;

/**
 * An exception that represents a failed action execution
 */
public class ActionFailedException extends RuntimeException {
    public ActionFailedException() {

    }

    public ActionFailedException(final String message) {
        super(message);
    }

    public ActionFailedException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public ActionFailedException(final Throwable throwable) {
        super(throwable);
    }
}
