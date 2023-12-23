package org.kurodev.progfrog.script.instructions.impl;

import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.RuntimeContext;
import org.kurodev.progfrog.script.instructions.StackFrame;

public class MoveInstruction extends StackFrame {
    public MoveInstruction(int lineNumber) {
        super("move", lineNumber);
    }

    @Override
    public void execute(ProgFrogGame gameContext, RuntimeContext context) {
        gameContext.getFrog().move();
    }
}
