package org.kurodev.progfrog.script.matchers;

import org.kurodev.progfrog.script.ParserContext;
import org.kurodev.progfrog.script.instructions.impl.PrintInstruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintMatcher implements ScriptMatcher {
    private static final Pattern printPattern = Pattern.compile("print\\s*\\((\\s*\\w+\\s*|\\s*\"[^\"]+\"\\s*)\\)\\s*;?");

    @Override
    public void handle(String line, ParserContext context) {
        Matcher m = printPattern.matcher(line);
        if (m.find()) {
            context.getStack().push(new PrintInstruction(context.getCurrentLine(), m.group(1)));
        }
    }
}
