package org.kurodev.progfrog.script.matchers;

import org.kurodev.progfrog.script.ParserContext;
import org.kurodev.progfrog.script.instructions.impl.MoveInstruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveMatcher implements ScriptMatcher {
    private static final Pattern movePattern = Pattern.compile("move\\s*\\(\\s*\\)\\s*;?");


    @Override
    public void handle(String line, ParserContext context) {
        Matcher m = movePattern.matcher(line);
        if (m.find()) {
            context.getStack().push(new MoveInstruction(context.getCurrentLine()));
        }
    }
}
