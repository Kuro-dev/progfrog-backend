package org.kurodev.progfrog.script;

import lombok.extern.slf4j.Slf4j;
import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.exceptions.ScriptException;
import org.kurodev.progfrog.script.exceptions.StackUnderflowException;
import org.kurodev.progfrog.script.instructions.StackFrame;
import org.kurodev.progfrog.script.matchers.ScriptMatcher;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
public class ProgFrogScriptParser {

    private static final Pattern variablePattern = Pattern.compile("^(int|boolean|String)\\s+(\\w+)\\s*=\\s*([\\w\"]+)\\s*;$");
    private static final List<ScriptMatcher> instructionSet = gatherInstructionSet();
    private final ParserContext globalContext = new ParserContext();

    private static List<ScriptMatcher> gatherInstructionSet() {
        log.info("Gathering instructionset for parser");
        List<ScriptMatcher> out = new ArrayList<>();
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackage("org.kurodev.progfrog")
                .setScanners(Scanners.SubTypes));
        Set<Class<? extends ScriptMatcher>> matchers = reflections.getSubTypesOf(ScriptMatcher.class);
        matchers.forEach(matcher -> {
            try {
                ScriptMatcher m = matcher.getConstructor().newInstance();
                out.add(m);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                //should never happen
                throw new RuntimeException(e);
            }
        });
        log.info("Registered {} instructions", out.size());
        return out;
    }

    public void parse(ProgFrogGame game, InputStream script) {
        parse(game, new Scanner(script));
    }

    public void parse(ProgFrogGame game, String script) {
        parse(game, new Scanner(script));
    }

    private void parse(ProgFrogGame game, Scanner scan) {
        long startTime = System.nanoTime();
        log.info("Parsing script");
        globalContext.setGame(game);
        while (scan.hasNextLine()) {
            String codeLine = ScriptUtils.normalize(scan.nextLine());
            if (codeLine.isBlank()) {
                continue;
            }
            for (ScriptMatcher scriptMatcher : instructionSet) {
                scriptMatcher.handle(codeLine, globalContext);
            }
            globalContext.incrementCurrentLine();
        }
        long endTime = System.nanoTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
        String timeStr = formatter.format(LocalTime.ofNanoOfDay(endTime - startTime));
        log.info("Successfully parsed script in {}", timeStr);
    }

    public List<String> getLogs() {
        return globalContext.getRuntimeContext().getLog();
    }

    public List<String> execute() throws ScriptException {
        RuntimeContext context = globalContext.getRuntimeContext();
        while (!globalContext.getStack().isEmpty()) {
            nextFrame();
        }
        return context.getLog();
    }

    public void nextFrame() throws ScriptException {
        RuntimeContext context = globalContext.getRuntimeContext();
        if (!globalContext.getStack().isEmpty()) {
            StackFrame frame = globalContext.getStack().removeLast();
            frame.execute(globalContext.getGame(), context);
        } else {
            throw new StackUnderflowException("Running out of script");
        }
    }

    public ParserContext getGlobalContext() {
        return globalContext;
    }
}
