package org.kurodev.progfrog.game.entity;

public class FrogException extends RuntimeException {
    public FrogException() {
    }

    public FrogException(String message) {
        super(message);
    }

    public FrogException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrogException(Throwable cause) {
        super(cause);
    }

    public FrogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
