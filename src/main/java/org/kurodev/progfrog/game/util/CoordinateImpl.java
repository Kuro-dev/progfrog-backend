package org.kurodev.progfrog.game.util;


public record CoordinateImpl(int x, int y) implements Coordinate {


    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
