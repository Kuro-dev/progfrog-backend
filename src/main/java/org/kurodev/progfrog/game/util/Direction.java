package org.kurodev.progfrog.game.util;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)

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

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    public String getName() {
        return name;
    }

}
