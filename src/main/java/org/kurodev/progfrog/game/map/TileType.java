package org.kurodev.progfrog.game.map;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum TileType {

    WALL("X"),

    FLOOR("0"),

    VOID("#"),
    ;
    private final String identifier;

    TileType(String identifier) {

        this.identifier = identifier;
    }

    public static TileType identify(char s) {
        return identify(String.valueOf(s));
    }

    public static TileType identify(String s) {
        for (TileType value : values()) {
            if (value.matches(s))
                return value;
        }
        return null;
    }

    public static TileType identifyStrict(char s) {
        return identifyStrict(String.valueOf(s));
    }

    public static TileType identifyStrict(String s) {
        for (TileType value : values()) {
            if (value.matches(s))
                return value;
        }
        throw new MapValidationException("Unrecognised tile: " + s);
    }

    @JsonValue
    public String getIdentifier() {
        return identifier;
    }

    private boolean matches(String s) {
        return s.equals(identifier);
    }

}
