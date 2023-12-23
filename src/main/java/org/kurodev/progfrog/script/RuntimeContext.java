package org.kurodev.progfrog.script;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kurodev.progfrog.script.instructions.StackFrame;
import org.kurodev.progfrog.script.var.ScriptVariable;

import java.util.*;

@AllArgsConstructor
@Getter
public class RuntimeContext {
    private final Deque<StackFrame> stack;

    private final List<String> log = new ArrayList<>();

    private final Map<String, ScriptVariable> variables = new HashMap<>();


}
