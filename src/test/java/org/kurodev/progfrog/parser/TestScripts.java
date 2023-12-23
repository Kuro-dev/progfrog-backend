package org.kurodev.progfrog.parser;

public class TestScripts {

    public static final String SCRIPT1 = """
            print("Starting the sequence")
                        
            // Move forward
            move()
            move()
            move()
            move()
            print("Moved 4 times")
                        
            // Turn
            turn()
            print("Turned 90° left")
                        
            // Move forward again
            move()
            print("Moved forward")
                        
            // Turn left
            turn()
            print("Turned")
                        
            // Final move
            move()
            print("Moved forward")
                        
            // End of sequence
            print("Sequence complete")
            """;
}
