package org.kurodev.progfrog.script.var;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ScriptVariable {
    private final ScriptVarType type;
    private String value;
}
