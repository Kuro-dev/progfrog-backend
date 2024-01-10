package org.kurodev.progfrog.api.dto.editor;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.kurodev.progfrog.api.dto.FrogDTO;
import org.kurodev.progfrog.game.map.MapEditor;
import org.kurodev.progfrog.game.util.Coordinate;

import java.util.Map;

public record MapEditorResponse(
        @NotNull String mapID,
        @NotNull String map,
        @Nullable FrogDTO frog,
        @NotNull Map<Coordinate, Integer> foodItems) {

    public static MapEditorResponse fromEditor(String id, MapEditor editor) {
        FrogDTO frog = null;
        if (editor.getFrogPosition() != null) {
            frog = new FrogDTO(editor.getFrogPosition(), editor.getFrogDirection());
        }
        return new MapEditorResponse(id, editor.toString(), frog, editor.getFoodItems());
    }
}
