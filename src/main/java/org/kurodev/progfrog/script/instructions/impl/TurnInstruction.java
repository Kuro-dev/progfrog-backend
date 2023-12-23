package org.kurodev.progfrog.script.instructions.impl;

import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.RuntimeContext;
import org.kurodev.progfrog.script.instructions.StackFrame;

public class TurnInstruction extends StackFrame {
    public TurnInstruction(int lineNumber) {
        super("turn", lineNumber);
    }


    @Override
    public void execute(ProgFrogGame gameContext, RuntimeContext context) {
        gameContext.getFrog().turn();
    }
}
