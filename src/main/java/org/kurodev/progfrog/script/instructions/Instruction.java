package org.kurodev.progfrog.script.instructions;

import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.RuntimeContext;
import org.kurodev.progfrog.script.exceptions.ScriptException;

public interface Instruction {

    void execute(ProgFrogGame game, RuntimeContext context) throws ScriptException;
}
