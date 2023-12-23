package org.kurodev.progfrog.script.matchers;

import org.kurodev.progfrog.script.ParserContext;

/**
 * Any subclass of this must have a 0 arg constructor.
 * Any subclass of this will be automatically loaded into the instruction-set.
 */
public interface ScriptMatcher {

    void handle(String line, ParserContext context);
}
