package org.kurodev.progfrog.script.stack;

import java.util.function.Supplier;

public interface MethodCall {

    static MethodCall of(String name, Runnable method) {
        return new TypedMethodCallImpl<Void>(name, () -> {
            method.run();
            return null;
        });
    }

    static <T> MethodCall of(String name, Supplier<T> method) {
        return new TypedMethodCallImpl<T>(name, method);
    }

    String getName();

    void execute();

    String toString();

    String getResult();

}
