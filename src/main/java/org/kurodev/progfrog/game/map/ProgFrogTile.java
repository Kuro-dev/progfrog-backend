package org.kurodev.progfrog.game.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kurodev.progfrog.game.util.Coordinate;

@AllArgsConstructor
@Getter
public class ProgFrogTile {
    private final Coordinate pos;
    private final TileType type;
}
