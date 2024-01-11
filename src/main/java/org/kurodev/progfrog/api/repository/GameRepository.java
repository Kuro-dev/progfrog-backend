package org.kurodev.progfrog.api.repository;

import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.game.map.MapEditor;
import org.kurodev.progfrog.script.JavaScriptManager;
import org.kurodev.progfrog.script.ScriptResult;

import java.util.Optional;

public interface GameRepository {

    String getUniqueID();

    default String storeGame(ProgFrogGame game) {
        return storeGame(getUniqueID(), game);
    }

    String storeGame(String id, ProgFrogGame game);

    Optional<ProgFrogGame> findGameByID(String id);

    default String storeScriptResult(ScriptResult result) {
        return storeScriptResult(getUniqueID(), result);
    }

    String storeScriptResult(String id, ScriptResult result);

    Optional<ScriptResult> findScriptById(String id);

    Optional<MapEditor> findEditorById(String id);

    String storeEditor(MapEditor editor);
}
