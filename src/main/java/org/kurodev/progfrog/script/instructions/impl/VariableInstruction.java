package org.kurodev.progfrog.script.instructions.impl;

import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.RuntimeContext;
import org.kurodev.progfrog.script.exceptions.ScriptException;
import org.kurodev.progfrog.script.instructions.StackFrame;

public class VariableInstruction extends StackFrame {
    public VariableInstruction(int lineNumber, String name, String value) {
        super("var", lineNumber);
    }

    @Override
    public void execute(ProgFrogGame game, RuntimeContext context) throws ScriptException {

    }
}
