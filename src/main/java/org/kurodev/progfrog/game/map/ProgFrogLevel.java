package org.kurodev.progfrog.game.map;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProgFrogLevel {
    private final int width, height;
    private final List<ProgFrogTile> tiles;
}
