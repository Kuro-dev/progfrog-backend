package org.kurodev.progfrog.parser;

import org.junit.jupiter.api.Test;
import org.kurodev.progfrog.game.map.ProgFrogLevel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapParserTests {

    @Test
    public void serializedMapShouldStayTheSame() {
        String map = """
                XXXXXXXXXX
                XXXXXXXXXX
                XXXXXXXXXX
                XXXXXXXXXX
                XXXXXXXXXX
                XXXXXXXXXX
                XXXXX5XXXX
                XXXXXXXXXX
                XXXXXXXXXX
                XXXXXXXXXX
                """;
        var level = ProgFrogLevel.fromString(map,"\n");
        assertEquals(map, level.toString());
    }
}
