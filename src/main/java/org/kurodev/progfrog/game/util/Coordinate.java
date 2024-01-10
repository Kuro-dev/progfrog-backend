package org.kurodev.progfrog.game.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotNull;

public interface Coordinate {
    @JsonCreator
    static Coordinate of(int x, int y) {
        return new CoordinateImpl(x, y);
    }

    @NotNull
    int x();

    @NotNull
    int y();
    @NotNull
    default int getX(){
        return x();
    };

    @NotNull
    default int getY(){
        return y();
    };

    default Coordinate add(Coordinate other) {
        return of(x() + other.x(), y() + other.y());
    }

    @Override
    boolean equals(Object other);

    @Override
    int hashCode();
}
