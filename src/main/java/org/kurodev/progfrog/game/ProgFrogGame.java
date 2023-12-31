package org.kurodev.progfrog.game;

import org.kurodev.progfrog.game.entity.FrogEntity;
import org.kurodev.progfrog.game.map.ProgFrogLevel;
import org.kurodev.progfrog.game.util.Coordinate;
import org.kurodev.progfrog.game.util.Direction;

import java.util.UUID;

public class ProgFrogGame {
    private final String gameId;
    private final FrogEntity initialState;
    private final ProgFrogLevel level;
    private FrogEntity frog;

    public ProgFrogGame(ProgFrogLevel level, Direction initialFrogDirection, Coordinate initialFrogPosition) {
        this(UUID.randomUUID().toString(), level, initialFrogDirection, initialFrogPosition);
    }

    public ProgFrogGame(String gameId, ProgFrogLevel level, Direction initialFrogDirection, Coordinate initialFrogPosition) {
        this.gameId = gameId;
        this.level = level;
        this.frog = new FrogEntity(level, initialFrogDirection, initialFrogPosition);
        initialState = new FrogEntity(frog);
    }

    public ProgFrogLevel getLevel() {
        return level;
    }

    public void setLevel(String mapString) {
        setLevel(mapString, null);
    }

    public void setLevel(String mapString, String delimiter) {
        this.level.update(mapString, delimiter);
    }

    public FrogEntity getFrog() {
        return frog;
    }

    public void reset() {
        frog = initialState;
        level.reset();
    }
}
