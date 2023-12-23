package org.kurodev.progfrog.script.instructions.impl;

import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.RuntimeContext;
import org.kurodev.progfrog.script.exceptions.ScriptException;
import org.kurodev.progfrog.script.instructions.StackFrame;

public class PrintInstruction extends StackFrame {

    public PrintInstruction(int lineNumber, String... params) {
        super("print", lineNumber, params);
    }

    @Override
    public void execute(ProgFrogGame game, RuntimeContext context) throws ScriptException {
        if (getParams().length != 1) {
            throw new ScriptException("Argument mismatch expected 1, but got " + getParams().length, getLineNumber());
        }
        context.getLog().add(getParams()[0]);
    }
}
