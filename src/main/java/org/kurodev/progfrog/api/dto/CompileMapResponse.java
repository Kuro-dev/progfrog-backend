package org.kurodev.progfrog.api.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import org.springdoc.core.annotations.ParameterObject;

@ParameterObject

public class CompileMapResponse {
    @Parameter(description = "The ID of the map")
    @NotNull
    private final String mapID;

    public CompileMapResponse(String mapID) {
        this.mapID = mapID;
    }

    public String getMapID() {
        return mapID;
    }
}
