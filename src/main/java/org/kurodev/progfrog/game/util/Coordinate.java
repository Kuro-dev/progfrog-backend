package org.kurodev.progfrog.game.util;

public interface Coordinate {
    static Coordinate of(int x, int y) {
        return new CoordinateImpl(x, y);
    }

    int getX();

    int getY();

    default Coordinate add(Coordinate other) {
        return new CoordinateImpl(getX() + other.getX(), getY() + other.getY());
    }
}
