package org.kurodev.progfrog.game.map;

import org.kurodev.progfrog.game.util.Coordinate;

public class ProgFrogLevel {
    private ProgFrogTile[][] tiles;
    private ProgFrogTile[][] initialState;


    private ProgFrogLevel(ProgFrogTile[][] tiles) {
        this.tiles = tiles;
        this.initialState = tiles.clone();

        deepClone(this.tiles, initialState);
    }

    public static ProgFrogLevel fromString(String map) {
        return fromString(map, null);
    }

    public static ProgFrogLevel fromString(String map, String delimiter) {
        String[] rows = map.trim().split(delimiter != null ? delimiter : "\n");
        ProgFrogTile[][] tiles = new ProgFrogTile[rows.length][];
        for (int y = 0; y < rows.length; y++) {
            ProgFrogTile[] row = new ProgFrogTile[rows[y].length()];
            tiles[y] = row;
            for (int x = 0; x < rows[y].toCharArray().length; x++) {
                row[x] = ProgFrogTile.fromString(rows[y].charAt(x));
            }
        }
        return new ProgFrogLevel(tiles);
    }

    private void deepClone(ProgFrogTile[][] src, ProgFrogTile[][] dest) {
        for (int y = 0; y < src.length; y++) {
            for (int x = 0; x < src[y].length; x++) {
                if (src[y][x] != null)
                    dest[y][x] = src[y][x].copy();
            }
        }
    }

    private boolean isOOB(int x, int y) {
        if (y >= tiles.length) {
            return true;
        }
        return x >= tiles[y].length;
    }

    public ProgFrogTile getTile(int x, int y) {
        if (isOOB(x, y)) {
            return ProgFrogTile.voidTile();
        }
        return tiles[y][x];
    }

    public ProgFrogTile getTile(Coordinate pos) {
        return getTile(pos.getX(), pos.getY());
    }

    public void setTile(int x, int y, ProgFrogTile newTile) {
        tiles[y][x] = newTile;
    }

    public void reset() {
        deepClone(initialState, tiles);
    }

    public ProgFrogTile[][] getTiles() {
        return tiles;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        ProgFrogTile fallback = ProgFrogTile.wallTile();
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                ProgFrogTile tile = getTile(x, y);
                if (tile == null) {
                    tile = fallback;
                }
                out.append(tile);
            }
            out.append("\n");
        }
        return out.toString();
    }

    public void update(String mapString, String delimiter) {
        ProgFrogLevel newLevel = fromString(mapString, delimiter);
        this.tiles = newLevel.tiles;
        this.initialState = newLevel.initialState;
    }
}
