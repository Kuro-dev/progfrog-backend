package org.kurodev.progfrog.script.var;

public enum ScriptVarType {
    INTEGER("int"),
    STRING("string"),
    BOOLEAN("boolean"),
    ;

    private final String identifier;

    ScriptVarType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
