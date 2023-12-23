package org.kurodev.progfrog.game.util;

import lombok.Getter;

@Getter
public enum Direction implements Coordinate {
    NORTH(0, -1),
    SOUTH(0, 1),
    WEST(-1, 0),
    EAST(1, 0);
    private final int x, y;
    private final String name;

    Direction(int x, int y) {
        this.name = name();
        this.x = x;
        this.y = y;
    }

}
