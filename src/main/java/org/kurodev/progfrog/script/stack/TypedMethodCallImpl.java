package org.kurodev.progfrog.script.stack;

import java.util.function.Supplier;

public class TypedMethodCallImpl<T> implements MethodCall {
    private final String name;
    private final Supplier<T> method;

    private T result = null;

    public TypedMethodCallImpl(String name, Supplier<T> method) {
        this.name = name;
        this.method = method;
    }


    public String getName() {
        return name;
    }

    public void execute() {
        result = method.get();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getResult() {
        if (result == null) {
            return null;
        }
        return String.valueOf(result);
    }
}
