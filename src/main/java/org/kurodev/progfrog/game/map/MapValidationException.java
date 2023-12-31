package org.kurodev.progfrog.game.map;

public class MapValidationException extends RuntimeException{
    public MapValidationException() {
    }

    public MapValidationException(String message) {
        super(message);
    }

    public MapValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapValidationException(Throwable cause) {
        super(cause);
    }

    public MapValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
