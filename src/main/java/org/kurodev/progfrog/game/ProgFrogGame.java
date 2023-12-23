package org.kurodev.progfrog.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.kurodev.progfrog.game.entity.Frog;
import org.kurodev.progfrog.game.map.ProgFrogLevel;

@AllArgsConstructor
@Builder
@Getter
public class ProgFrogGame {
    private final ProgFrogLevel level;
    private final Frog frog;

}
