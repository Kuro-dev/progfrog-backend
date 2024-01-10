package org.kurodev.progfrog.api.dto;

import jakarta.validation.constraints.NotNull;
import org.kurodev.progfrog.game.util.Coordinate;
import org.kurodev.progfrog.game.util.Direction;

public record FrogDTO(
        @NotNull Coordinate position,
        @NotNull Direction direction) {
}
