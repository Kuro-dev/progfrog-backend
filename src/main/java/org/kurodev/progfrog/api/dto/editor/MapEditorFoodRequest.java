package org.kurodev.progfrog.api.dto.editor;

import org.kurodev.progfrog.game.util.Coordinate;

public record MapEditorFoodRequest(Coordinate position, int foodCount) {
}
