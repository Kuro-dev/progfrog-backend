package org.kurodev.progfrog.api.dto.game;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import org.kurodev.progfrog.api.dto.editor.MapDTO;
import org.kurodev.progfrog.game.map.MapEditor;
import org.kurodev.progfrog.script.ScriptResult;


public record ScriptResultDTO(
        @Parameter(description = "The ID of the result")
        @NotNull String resultID,

        @Parameter(description = "The amount of stackframes")
        @NotNull int currentFrame,
        @Parameter(description = "The amount of stackframes")
        @NotNull int stackframes,

        @Parameter(description = "The map in its current state")
        @NotNull MapDTO map) {

    public static ScriptResultDTO of(String resultID, String mapID, ScriptResult game) {
        return new ScriptResultDTO(resultID, 0, game.getCallStack().size(), MapDTO.fromGame(mapID, game.getGame()));
    }
}
