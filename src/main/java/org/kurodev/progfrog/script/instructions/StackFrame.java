package org.kurodev.progfrog.script.instructions;

import lombok.Getter;

import java.util.Arrays;

@Getter
public abstract class StackFrame implements Instruction {
    private final String functionName;
    private final int lineNumber;
    private final String[] params;

    public StackFrame(String functionName, int lineNumber, String... params) {
        this.functionName = functionName;
        this.lineNumber = lineNumber;
        this.params = params;
    }

    @Override
    public String toString() {
        return lineNumber + ": " + functionName + (params.length > 0 ? Arrays.toString(params) : "");
    }
}
