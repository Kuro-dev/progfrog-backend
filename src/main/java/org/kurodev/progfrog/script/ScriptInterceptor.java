package org.kurodev.progfrog.script;

import org.kurodev.progfrog.game.entity.Frog;

import java.lang.reflect.Method;

class ScriptInterceptor {
    private static final String defaultImport = """
            package progfrog.script;
            import org.kurodev.progfrog.game.entity.Frog;
            """;
    private static final String classDeclaration = """
            public class Script implements Frog{
            private Frog frog;
                        
            public Script(Frog frog){
                this.frog = frog;
            }
            """;
    private static final String classDeclarationEnding = "}";
    private static final String methodDeclarations = constructGenericMethodDeclarations();

    public static String intercept(String script) {
        return defaultImport + classDeclaration + methodDeclarations + script + classDeclarationEnding;
    }

    private static String constructGenericMethodDeclarations() {
        return constructGenericMethodDeclarations("");
    }

    private static String constructGenericMethodDeclarations(String delimiter) {
        boolean delimiterPresent = !delimiter.isEmpty();
        StringBuilder output = new StringBuilder();
        Method[] methods = Frog.class.getDeclaredMethods();
        for (Method method : methods) {
            String name = method.getName();
            output.append("public ").append(method.getReturnType()).append(" ").append(name).append("(){").append(delimiter);
            if (delimiterPresent) {
                output.append("\t");
            }
            if (!"void".equals(method.getReturnType().toString())) {
                output.append("return ");
            }
            output.append("frog.").append(name).append("();").append(delimiter).append("}").append(delimiter);
        }
        return output.toString();
    }
}
