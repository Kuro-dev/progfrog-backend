package org.kurodev.progfrog.game;

import org.kurodev.progfrog.game.entity.FrogEntity;
import org.kurodev.progfrog.game.map.ProgFrogLevel;
import org.kurodev.progfrog.game.util.Coordinate;
import org.kurodev.progfrog.game.util.Direction;

import java.util.UUID;

public class ProgFrogGame {
    private final String mapID;
    private final ProgFrogLevel level;
    private final FrogEntity frog;

    public ProgFrogGame(ProgFrogLevel level, Direction initialFrogDirection, Coordinate initialFrogPosition) {
        this(UUID.randomUUID().toString(), level, initialFrogDirection, initialFrogPosition);
    }

    public ProgFrogGame(String mapID, ProgFrogLevel level, Direction initialFrogDirection, Coordinate initialFrogPosition) {
        this.mapID = mapID;
        this.level = level;
        this.frog = new FrogEntity(level, initialFrogDirection, initialFrogPosition);
    }

    public ProgFrogLevel getLevel() {
        return level;
    }

    public FrogEntity getFrog() {
        return frog;
    }

}
