package org.kurodev.progfrog.game.map;

/**
 * Instances of this class must always be immutable.
 */
public class ProgFrogTile {
    private static final ProgFrogTile WALL_TILE = new ProgFrogTile(null, TileType.WALL);
    private static final ProgFrogTile VOID_TILE = new ProgFrogTile(null, TileType.VOID);
    private final TileType type;
    private Integer foodCount;

    public ProgFrogTile(Integer foodCount, TileType type) {
        this.foodCount = foodCount;
        this.type = type;
    }

    public static ProgFrogTile floorTile() {
        return floorTile(0);
    }

    public static ProgFrogTile floorTile(int foodCount) {
        return new ProgFrogTile(foodCount, TileType.FLOOR);
    }

    public static ProgFrogTile wallTile() {
        return WALL_TILE;
    }

    public static ProgFrogTile voidTile() {
        return VOID_TILE;
    }

    public static ProgFrogTile fromString(char c) {
        if (Character.isDigit(c)) {
            return floorTile(Character.digit(c, 10));
        }
        if (c == '#') {
            return voidTile();
        }
        if (c == 'X') {
            return wallTile();

        }
        throw new MapValidationException("Unrecognised character in Map-string: " + c);
    }

    public void addFood() {
        if (type != TileType.WALL)
            if (foodCount == null) {
                foodCount = 1;
            } else {
                foodCount++;
            }
    }

    public void removeFood() {
        if (type != TileType.WALL && foodCount > 0) {
            foodCount--;
        }
    }

    public TileType getType() {
        return type;
    }

    public ProgFrogTile copy() {
        return new ProgFrogTile(this.foodCount, this.type);
    }

    public boolean hasFood() {
        return foodCount != null && foodCount > 0;
    }

    public Integer getFoodCount() {
        return foodCount;
    }

    public String toString() {
        return switch (type) {
            case WALL -> "X";
            case FLOOR -> String.valueOf(foodCount);
            case VOID -> "#";
        };
    }
}
