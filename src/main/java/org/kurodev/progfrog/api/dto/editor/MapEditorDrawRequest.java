package org.kurodev.progfrog.api.dto.editor;

import jakarta.validation.constraints.NotNull;
import org.kurodev.progfrog.game.map.TileType;
import org.kurodev.progfrog.game.util.Coordinate;


public record MapEditorDrawRequest(@NotNull Coordinate pos,@NotNull TileType type) {
}
