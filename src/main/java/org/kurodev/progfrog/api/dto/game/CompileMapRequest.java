package org.kurodev.progfrog.api.dto.game;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.game.map.ProgFrogLevel;

public record CompileMapRequest(
        @Parameter(description = "Optional mapId to override an existing map.")
        @Nullable
        String mapId,
        @Parameter(description = "The map represented as a string of characters")
        @NotNull
        String map,
        @Parameter(description = "Optional delimiter. Default is \\n")
        @Nullable
        String rowDelimiter) {
}
