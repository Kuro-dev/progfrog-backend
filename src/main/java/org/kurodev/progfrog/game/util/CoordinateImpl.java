package org.kurodev.progfrog.game.util;


import java.util.Objects;


public final class CoordinateImpl implements Coordinate {
    private final int x;
    private final int y;
    public CoordinateImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateImpl that = (CoordinateImpl) o;
        return x == that.x && y == that.y;
    }


    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
