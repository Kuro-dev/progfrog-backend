package org.kurodev.progfrog.game.util;


import jakarta.validation.constraints.NotNull;

public record CoordinateImpl(@NotNull int x, @NotNull int y) implements Coordinate {


    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
