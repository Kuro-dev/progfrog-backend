package org.kurodev.progfrog.game.entity;

import org.kurodev.progfrog.game.map.ProgFrogLevel;
import org.kurodev.progfrog.game.map.ProgFrogTile;
import org.kurodev.progfrog.game.map.TileType;
import org.kurodev.progfrog.game.util.Coordinate;
import org.kurodev.progfrog.game.util.Direction;

public class FrogEntity implements Frog {
    private final ProgFrogLevel level;
    private int foodCount = 0;
    private Direction direction;
    private Coordinate position;

    public FrogEntity(ProgFrogLevel level, Direction direction, Coordinate position) {
        this.level = level;
        this.direction = direction;
        this.position = position;
    }

    public FrogEntity(FrogEntity other) {
        this.level = other.level;
        this.direction = other.direction;
        this.position = other.position;
        this.foodCount = other.foodCount;
    }


    public Direction getDirection() {
        return direction;
    }

    public Coordinate getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "FrogEntity{" + "direction=" + direction + ", position=" + position + '}';
    }

    /**
     * Move one tile in the direction
     */
    public void move() {
        if (checkNextTile()) {
            this.position = this.position.add(direction);
        } else {
            throw new FrogException("The frog tried to move to an invalid tile.");
        }
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

    private boolean checkNextTile() {
        final Coordinate nextPos = this.position.add(direction);
        return level.getTile(nextPos).getType() == TileType.FLOOR;
    }

    public boolean peek() {
        return checkNextTile();
    }

    public boolean checkFood() {
        return level.getTile(position).hasFood();
    }

    public void eat() {
        ProgFrogTile tile = level.getTile(position);
        if (tile.hasFood()) {
            tile.removeFood();
            foodCount++;
        } else {
            throw new FrogException("There is no food at the frogs position.");
        }
    }

    public void drop() {
        if (foodCount > 0) {
            level.getTile(position).addFood();
            foodCount--;
        } else {
            throw new FrogException("Cannot drop food, frog has none.");
        }
    }
}
