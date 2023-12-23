package org.kurodev.progfrog.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.kurodev.progfrog.game.util.Coordinate;
import org.kurodev.progfrog.game.util.Direction;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class Frog {
    private final String id;
    private Direction direction;
    private Coordinate position;

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Move one tile in the direction
     */
    public void move() {
        this.position = this.position.add(direction);
    }

    /**
     * Turn the frog 90° left
     */
    public void turn() {
        switch (direction) {
            case EAST -> direction = Direction.NORTH;
            case SOUTH -> direction = Direction.EAST;
            case WEST -> direction = Direction.SOUTH;
            case NORTH -> direction = Direction.WEST;
        }
    }

}
