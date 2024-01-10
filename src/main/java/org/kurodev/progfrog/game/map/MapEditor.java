package org.kurodev.progfrog.game.map;

import org.kurodev.progfrog.game.util.Coordinate;
import org.kurodev.progfrog.game.util.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapEditor {
    private final List<List<TileType>> map;
    private final Map<Coordinate, Integer> foodItems = new HashMap<>();
    private Coordinate frogPosition;
    private Direction frogDirection;

    private MapEditor() {
        map = new ArrayList<>();
    }

    public static MapEditor emptyMap(int initialWidth, int initialHeight) {
        MapEditor editor = new MapEditor();
        editor.init(initialWidth, initialHeight);
        return editor;
    }

    public Coordinate getFrogPosition() {
        return frogPosition;
    }

    public void setFrogPosition(Coordinate position) {

        this.frogPosition = position;
    }

    public Direction getFrogDirection() {
        return frogDirection;
    }

    public void setFrogDirection(Direction direction) {
        this.frogDirection = direction;
    }

    public boolean isOOB(Coordinate pos) {
        if (pos.y() >= map.size()) {
            return true;
        }
        return pos.x() >= map.get(pos.y()).size();
    }

    public TileType getTile(Coordinate pos) {
        if (isOOB(pos)) {
            return TileType.VOID;
        }
        return map.get(pos.y()).get(pos.x());
    }

    private void deleteTile(Coordinate pos) {
        map.get(pos.y()).remove(pos.x());
    }

    public void setTile(Coordinate pos, TileType type) {
        if (type == TileType.NONE) {
            deleteTile(pos);
            return;
        }
        if (pos.y() >= map.size()) {
            int diff = 1 + pos.y() - map.size();
            for (int i = 0; i < diff; i++) {
                map.add(new ArrayList<>());
            }
        }
        List<TileType> row = map.get(pos.y());
        if (pos.x() >= row.size()) {
            int diff = 1 + pos.x() - row.size();
            for (int i = 0; i < diff; i++) {
                row.add(TileType.VOID);
            }
        }
        if (pos.equals(frogPosition)) {
            setFrogPosition(null);
            setFrogDirection(null);
        }
        foodItems.remove(pos);
        row.set(pos.x(), type);
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

    public void setFood(Coordinate pos, int foodcount) {
        if (foodcount > 0) {
            foodItems.put(pos, foodcount);
        } else {
            foodItems.remove(pos);
        }
    }

    public int getFoodcount(Coordinate pos) {
        return foodItems.getOrDefault(pos, 0);
    }

    public String toString(String delimiter) {
        StringBuilder out = new StringBuilder();
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).size(); x++) {
                TileType tile = getTile(Coordinate.of(x, y));
                if (tile == null) {
                    System.out.println(out);
                    System.out.println(x + ", " + y);
                }
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

    public void setMap(String map) {
        this.map.clear();
        String[] rows = map.split("\n");
        for (int y = 0; y < rows.length; y++) {
            for (int x = 0; x < rows[y].length(); x++) {
                setTile(Coordinate.of(x, y), TileType.identifyStrict(rows[y].charAt(x)));
            }
        }
    }

    public Map<Coordinate, Integer> getFoodItems() {
        return foodItems;
    }
}
