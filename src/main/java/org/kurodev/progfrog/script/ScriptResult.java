package org.kurodev.progfrog.script;


import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.stack.MethodCall;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class ScriptResult {
    @Parameter(description = "The callstack of frog methods. e.g.: move, turn, peek")
    @NotNull
    private final List<MethodCall> callStack = new ArrayList<>();
    private final ProgFrogGame game;
    @Nullable
    private String exception;

    public ScriptResult(ProgFrogGame game) {
        this.game = game;
    }

    public List<MethodCall> getCallStack() {
        return callStack;
    }

    @Nullable
    public String getException() {
        return exception;
    }

    public void setException(@Nullable ScriptException exception) {
        this.exception = exception.toString();
    }

    public ProgFrogGame getGame() {
        return game;
    }
}
