package org.kurodev.progfrog.api.dto.editor;

import jakarta.validation.constraints.NotNull;

public record MapEditorResponse(
        @NotNull String mapID,
        @NotNull String map) {

}
