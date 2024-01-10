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

    default Coordinate add(Coordinate other) {
        return new CoordinateImpl(x() + other.x(), y() + other.y());
    }

    @Override
    boolean equals(Object other);

    @Override
    int hashCode();
}
