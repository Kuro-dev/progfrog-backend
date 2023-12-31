package org.kurodev.progfrog.api.repository;

import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.game.map.ProgFrogLevel;
import org.kurodev.progfrog.script.JavaScriptManager;

import java.util.Optional;

public interface GameRepository {

    String getUniqueID();

    default String storeGame(ProgFrogGame game) {
        return storeGame(getUniqueID(), game);
    }

    String storeGame(String id, ProgFrogGame game);

    default String storeScript(JavaScriptManager script) {
        return storeScript(getUniqueID(), script);
    }

    String storeScript(String id, JavaScriptManager script);

    Optional<ProgFrogGame> findGameByID(String id);

    Optional<JavaScriptManager> findScriptById(String id);

    default void updateMap(String gameId, String level) {
        findGameByID(gameId).ifPresent(found -> {
            found.setLevel(level);
            storeGame(gameId, found);
        });
    }

}
