package org.kurodev.progfrog.api.dto.game;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;

public record CompileScriptRequest(
        @Parameter(description = "The id of the compiled map")
        @NotNull
        String mapID,

        @Parameter(description = "The java code.")
        @NotNull
        String script) {
    public CompileScriptRequest(String mapID, String script) {
        this.mapID = mapID;
        this.script = script;
    }
}
