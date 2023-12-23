package org.kurodev.progfrog.script.matchers;

import org.kurodev.progfrog.script.ParserContext;
import org.kurodev.progfrog.script.instructions.impl.TurnInstruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurnMatcher implements ScriptMatcher {
    private static final Pattern turnPattern = Pattern.compile("turn\\s*\\(\\s*\\)\\s*;?");

    @Override
    public void handle(String line, ParserContext context) {
        Matcher m = turnPattern.matcher(line);
        if (m.find()) {
            context.getStack().push(new TurnInstruction(context.getCurrentLine()));
        }
    }
}
