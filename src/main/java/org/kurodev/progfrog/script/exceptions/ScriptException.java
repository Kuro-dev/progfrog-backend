package org.kurodev.progfrog.script.exceptions;

public class ScriptException extends Exception {
    public ScriptException(int line) {
        super(String.format("An unexpected error occurred at line %d: ", line));
    }

    public ScriptException(String message, int line) {
        super(String.format("An unexpected error occurred at line %d: ", line) + message);
    }

    public ScriptException(String message, int line, Throwable cause) {
        super(String.format("An unexpected error occurred at line %d: ", line) + message, cause);
    }

}
