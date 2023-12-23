package org.kurodev.progfrog.script;

public class ScriptUtils {
    public static String normalize(String script) {
        script = removeComments(script);
        return script;
    }

    /**
     * Removes comments from a multiline string.
     *
     * @param input The input multiline string.
     * @return The string with comments removed.
     */
    private static String removeComments(String input) {
        StringBuilder output = new StringBuilder();
        String[] lines = input.split("\n");
        for (String line : lines) {
            String trimmedLine = line.trim();
            int commentIndex = trimmedLine.indexOf("//");
            if (commentIndex != -1) {
                output.append(trimmedLine, 0, commentIndex);
            } else {
                output.append(trimmedLine);
            }
            output.append("\n");
        }
        return output.toString();
    }
}
