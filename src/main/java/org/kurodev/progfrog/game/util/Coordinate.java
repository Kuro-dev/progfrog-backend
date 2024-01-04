package org.kurodev.progfrog.game.util;

import com.fasterxml.jackson.annotation.JsonCreator;

public interface Coordinate {
    @JsonCreator
    static Coordinate of(int x, int y) {
        return new CoordinateImpl(x, y);
    }

    int getX();

    int getY();

    default Coordinate add(Coordinate other) {
        return new CoordinateImpl(getX() + other.getX(), getY() + other.getY());
    }
}
