package org.kurodev.progfrog.script.exceptions;

public class StackUnderflowException extends ScriptException {
    public StackUnderflowException(int line) {
        super(line);
    }

    public StackUnderflowException(String message) {
        super(message, -1);
    }

    public StackUnderflowException(String message, int line) {
        super(message, line);
    }

    public StackUnderflowException(String message, int line, Throwable cause) {
        super(message, line, cause);
    }
}
