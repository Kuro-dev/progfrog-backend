package org.kurodev.progfrog.script;


import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import org.kurodev.progfrog.script.stack.MethodCall;

import java.util.ArrayList;
import java.util.List;

public class ScriptResult {
    @Parameter(description = "The callstack of frog methods. e.g.: move, turn, peek")
    private final List<MethodCall> callStack = new ArrayList<>();
    @Nullable
    private StackTraceElement[] exceptionStacktrace;

    public List<MethodCall> getCallStack() {
        return callStack;
    }

    @Nullable
    public StackTraceElement[] getExceptionStacktrace() {
        return exceptionStacktrace;
    }

    public void setStacktrace(@Nullable StackTraceElement[] exception) {
        this.exceptionStacktrace = exception;
    }

    public void reset() {
        callStack.clear();
        exceptionStacktrace = null;
    }
}
