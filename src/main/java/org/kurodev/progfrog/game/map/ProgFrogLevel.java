package org.kurodev.progfrog.game.map;

import org.kurodev.progfrog.game.util.Coordinate;

public class ProgFrogLevel {
    private TileType[][] tiles;
    private TileType[][] initialState;


    private ProgFrogLevel(TileType[][] tiles) {
        this.tiles = tiles;
        this.initialState = tiles.clone();

        deepClone(this.tiles, initialState);
    }

    public static ProgFrogLevel fromString(String map) {
        return fromString(map, null);
    }

    public static ProgFrogLevel fromString(String map, String delimiter) {
        String[] rows = map.trim().split(delimiter != null ? delimiter : "\n");
        TileType[][] tiles = new TileType[rows.length][];
        for (int y = 0; y < rows.length; y++) {
            TileType[] row = new TileType[rows[y].length()];
            tiles[y] = row;
            for (int x = 0; x < rows[y].toCharArray().length; x++) {
                row[x] = TileType.identifyStrict(rows[y].charAt(x));
            }
        }
        return new ProgFrogLevel(tiles);
    }

    private void deepClone(TileType[][] src, TileType[][] dest) {
        for (int y = 0; y < src.length; y++) {
            System.arraycopy(src[y], 0, dest[y], 0, src[y].length);
        }
    }

    private boolean isOOB(int x, int y) {
        if (y >= tiles.length) {
            return true;
        }
        return x >= tiles[y].length;
    }

    public TileType getTile(int x, int y) {
        if (isOOB(x, y)) {
            return TileType.VOID;
        }
        return tiles[y][x];
    }

    public TileType getTile(Coordinate pos) {
        return getTile(pos.x(), pos.y());
    }

    public void setTile(int x, int y, TileType newTile) {
        tiles[y][x] = newTile;
    }

    public void reset() {
        deepClone(initialState, tiles);
    }

    public TileType[][] getTiles() {
        return tiles;
    }

    public String toString(String delimiter) {
        StringBuilder out = new StringBuilder();
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                TileType tile = getTile(x, y);
                out.append(tile.getIdentifier());
            }
            out.append(delimiter);
        }
        return out.toString();
    }

    @Override
    public String toString() {
        return toString("\n");
    }

    public void update(String mapString, String delimiter) {
        ProgFrogLevel newLevel = fromString(mapString, delimiter);
        this.tiles = newLevel.tiles;
        this.initialState = newLevel.initialState;
    }

    public boolean hasFood(Coordinate position) {
        return false;
    }

    public boolean tryRemoveFood(Coordinate position) {
        return true;
    }

    public void addFood(Coordinate position) {

    }
}
