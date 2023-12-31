package org.kurodev.progfrog.api.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import org.kurodev.progfrog.game.util.Coordinate;
import org.kurodev.progfrog.game.util.Direction;
import org.springdoc.core.annotations.ParameterObject;

public record FrogDataDTO(
        @Parameter(description = "The starting position of the frog. Must be an unoccupied tile.")
        @NotNull
        Coordinate frogPosition,
        @Parameter(description = "The initial direction the frog should be facing.")
        @NotNull
        Direction initialDirection) {
    public FrogDataDTO(Coordinate frogPosition, Direction initialDirection) {
        this.frogPosition = frogPosition;
        this.initialDirection = initialDirection;
    }

    @Override
    public Coordinate frogPosition() {
        return frogPosition;
    }
}
