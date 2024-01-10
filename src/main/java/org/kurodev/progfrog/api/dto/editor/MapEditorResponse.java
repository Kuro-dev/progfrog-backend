package org.kurodev.progfrog.api.dto.editor;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.kurodev.progfrog.api.dto.FrogDTO;
import org.kurodev.progfrog.game.map.MapEditor;
import org.kurodev.progfrog.game.util.Coordinate;

public record MapEditorResponse(
        @NotNull String mapID,
        @NotNull String map,
        @Nullable FrogDTO frog,
        @NotNull FoodItemDTO[] foodItems) {

    public static MapEditorResponse fromEditor(String id, MapEditor editor) {
        FrogDTO frog = null;
        if (editor.getFrogPosition() != null) {
            frog = new FrogDTO(editor.getFrogPosition(), editor.getFrogDirection());
        }
        var foodEntries = editor.getFoodItems().entrySet();
        FoodItemDTO[] food = foodEntries.stream()
                .map(entry -> new FoodItemDTO(entry.getKey(), entry.getValue()))
                .toArray(FoodItemDTO[]::new);
        return new MapEditorResponse(id, editor.toString(), frog, food);
    }

    private record FoodItemDTO(@NotNull Coordinate pos, @NotNull Integer amount) {

    }
}
