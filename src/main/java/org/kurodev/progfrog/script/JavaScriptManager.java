package org.kurodev.progfrog.script;

import ch.obermuhlner.scriptengine.java.JavaScriptEngine;
import ch.obermuhlner.scriptengine.java.constructor.DefaultConstructorStrategy;
import ch.obermuhlner.scriptengine.java.execution.MethodExecutionStrategy;
import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.stack.InterceptedFrog;

import javax.script.CompiledScript;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptManager {
    private static final ScriptEngineManager manager = new ScriptEngineManager();
    private final ScriptResult result;
    private final JavaScriptEngine engine = (JavaScriptEngine) manager.getEngineByName("java");
    private final ProgFrogGame game;
    private CompiledScript compiledScript = null;

    public JavaScriptManager(ProgFrogGame game) {
        this.game = game;
        result = new ScriptResult(game);
        engine.setExecutionStrategyFactory((clazz) -> MethodExecutionStrategy.byMatchingArguments(clazz, "main"));
        engine.setConstructorStrategy(DefaultConstructorStrategy.byMatchingArguments(new InterceptedFrog(game, result.getCallStack())));
    }

    public ProgFrogGame getGame() {
        return game;
    }

    public void compile(String script) throws ScriptException {
        script = ScriptInterceptor.intercept(script);
        try {
            compiledScript = engine.compile(script);
        } catch (ScriptException e) {
            result.setException(e);
            throw e;
        }
    }

    public ScriptResult execute() throws ScriptException {
        try {
            compiledScript.eval();
        } catch (ScriptException e) {
            result.setException(e);
            throw e;
        }
        return result;
    }

    public ScriptResult getResult() {
        return result;
    }
}
