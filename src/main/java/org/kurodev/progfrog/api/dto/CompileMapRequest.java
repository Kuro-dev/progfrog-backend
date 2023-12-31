package org.kurodev.progfrog.api.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.game.entity.FrogEntity;
import org.kurodev.progfrog.game.map.ProgFrogLevel;
import org.springdoc.core.annotations.ParameterObject;

@ParameterObject
public record CompileMapRequest(
        FrogDataDTO frogData,
        @Parameter(description = "Optional mapId to override an existing map.")
        @Nullable
        String mapId,
        @Parameter(description = "The map represented as a string of characters")
        @NotNull
        String map,
        @Parameter(description = "Optional delimiter. Default is \\n")
        @Nullable
        String rowDelimiter) {

    public ProgFrogGame toGame() {
        ProgFrogLevel level = ProgFrogLevel.fromString(map, rowDelimiter);
        return new ProgFrogGame(level, frogData.initialDirection(), frogData.frogPosition());
    }
}
