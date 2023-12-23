package org.kurodev.progfrog.script;

import lombok.Getter;
import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.instructions.StackFrame;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
public class ParserContext {
    private final RuntimeContext runtimeContext = new RuntimeContext(new ArrayDeque<>());
    private ProgFrogGame game;
    private int currentLine = 0;

    public void incrementCurrentLine() {
        currentLine++;
    }


    public Deque<StackFrame> getStack() {
        return runtimeContext.getStack();
    }

    public void setGame(ProgFrogGame game) {
        this.game = game;
    }
}
