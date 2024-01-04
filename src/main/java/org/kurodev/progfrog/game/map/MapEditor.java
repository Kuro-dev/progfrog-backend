package org.kurodev.progfrog.game.map;

import org.kurodev.progfrog.game.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class MapEditor {
    private final List<List<TileType>> map;

    private MapEditor() {
        map = new ArrayList<>();
    }

    public static MapEditor emptyMap(int initialWidth, int initialHeight) {
        MapEditor editor = new MapEditor();
        editor.init(initialWidth, initialHeight);
        return editor;
    }

    private boolean isOOB(Coordinate pos) {
        if (pos.getY() >= map.size()) {
            return true;
        }
        return pos.getX() >= map.get(pos.getY()).size();
    }

    public TileType getTile(Coordinate pos) {
        if (isOOB(pos)) {
            return TileType.VOID;
        }
        return map.get(pos.getY()).get(pos.getX());
    }

    public void setTile(Coordinate pos, TileType type) {
        if (pos.getY() >= map.size()) {
            int diff = 1 + pos.getY() - map.size();
            for (int i = 0; i < diff; i++) {
                map.add(new ArrayList<>());
            }
        }
        List<TileType> row = map.get(pos.getY());
        if (pos.getX() >= row.size()) {
            int diff = 1 + pos.getX() - row.size();
            for (int i = 0; i < diff; i++) {
                row.add(TileType.VOID);
            }
        }
        row.set(pos.getX(), type);
    }

    private void init(int initialWidth, int initialHeight) {
        //draw top-left walls
        for (int i = 0; i < initialHeight; i++) {
            setTile(Coordinate.of(0, i), TileType.WALL);
            setTile(Coordinate.of(i, 0), TileType.WALL);
        }
        //Draw only empty tiles everywhere.
        for (int y = 1; y < initialHeight; y++) {
            for (int x = 1; x < initialWidth; x++) {
                setTile(Coordinate.of(x, y), TileType.FLOOR);
            }
        }
        //draw bottom-right walls
        for (int i = 0; i < initialWidth; i++) {
            setTile(Coordinate.of(initialWidth, i), TileType.WALL);
            setTile(Coordinate.of(i, initialHeight), TileType.WALL);
        }
        setTile(Coordinate.of(initialWidth, initialHeight), TileType.WALL);

    }

    public String toString(String delimiter) {
        StringBuilder out = new StringBuilder();
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).size(); x++) {
                TileType tile = getTile(Coordinate.of(x, y));
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
}
