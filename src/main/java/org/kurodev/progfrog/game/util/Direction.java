package org.kurodev.progfrog.game.util;


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
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

}
