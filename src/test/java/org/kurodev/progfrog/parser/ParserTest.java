package org.kurodev.progfrog.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.game.entity.Frog;
import org.kurodev.progfrog.game.map.ProgFrogLevel;
import org.kurodev.progfrog.game.util.Coordinate;
import org.kurodev.progfrog.game.util.Direction;
import org.kurodev.progfrog.script.ProgFrogScriptParser;
import org.kurodev.progfrog.script.exceptions.ScriptException;

import java.util.Collections;

public class ParserTest {
    ProgFrogScriptParser parser;
    ProgFrogLevel level;
    ProgFrogGame game;

    @BeforeEach
    public void prepare() {
        parser = new ProgFrogScriptParser();
        level = new ProgFrogLevel(300, 300, Collections.emptyList());
        game = new ProgFrogGame(level, new Frog("1", Direction.NORTH, Coordinate.of(150, 150)));
    }

    @Test
    public void testInstructionSet() throws ScriptException {
        parser.parse(game, "turn();\nturn();\nturn();\nturn();");
        Assertions.assertEquals(4, parser.getGlobalContext().getStack().size());
        parser.nextFrame();
        Assertions.assertEquals(3, parser.getGlobalContext().getStack().size());
        Assertions.assertEquals(Direction.WEST, game.getFrog().getDirection());

        parser.nextFrame();
        Assertions.assertEquals(2, parser.getGlobalContext().getStack().size());
        Assertions.assertEquals(Direction.SOUTH, game.getFrog().getDirection());

        parser.nextFrame();
        Assertions.assertEquals(1, parser.getGlobalContext().getStack().size());
        Assertions.assertEquals(Direction.EAST, game.getFrog().getDirection());

        parser.nextFrame();
        Assertions.assertEquals(0, parser.getGlobalContext().getStack().size());
        Assertions.assertEquals(Direction.NORTH, game.getFrog().getDirection());
    }
}
