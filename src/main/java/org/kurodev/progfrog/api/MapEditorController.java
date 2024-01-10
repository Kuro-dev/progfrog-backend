package org.kurodev.progfrog.api;

import jakarta.annotation.Nullable;
import org.kurodev.progfrog.api.dto.FrogDTO;
import org.kurodev.progfrog.api.dto.editor.MapEditorDrawRequest;
import org.kurodev.progfrog.api.dto.editor.MapEditorFoodRequest;
import org.kurodev.progfrog.api.dto.editor.MapEditorResponse;
import org.kurodev.progfrog.api.repository.GameRepository;
import org.kurodev.progfrog.game.map.MapEditor;
import org.kurodev.progfrog.game.map.TileType;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("MapEditorController")
@RequestMapping("/editor")
public class MapEditorController {

    private final GameRepository repository;

    public MapEditorController(GameRepository repository) {

        this.repository = repository;
    }

    @GetMapping("/create")
    @Description("Creates an empty map editor instance.")
    ResponseEntity<MapEditorResponse> createEmptyMap(@Nullable @RequestParam(defaultValue = "10") Integer initialWidth,
                                                     @Nullable @RequestParam(defaultValue = "10") Integer initialHeight) {
        MapEditor editor = MapEditor.emptyMap(initialWidth, initialHeight);
        String id = repository.storeEditor(editor);
        return ResponseEntity.ok(MapEditorResponse.fromEditor(id, editor));
    }

    @PostMapping("/draw")
    @Description("Draws a tile on a map.")
    ResponseEntity<MapEditorResponse> draw(String editorID, @RequestBody List<MapEditorDrawRequest> drawRequests) {
        var editor = repository.findEditorById(editorID);
        if (editor.isPresent()) {
            for (MapEditorDrawRequest req : drawRequests) {
                editor.get().setTile(req.pos(), req.type());
            }
            return ResponseEntity.ok(MapEditorResponse.fromEditor(editorID, editor.get()));

        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/setMap")
    @Description("Draws a tile on a map.")
    ResponseEntity<MapEditorResponse> setMap(String editorID, String map) {
        Optional<MapEditor> optional = repository.findEditorById(editorID);
        if (optional.isPresent()) {
            MapEditor editor = optional.get();
            editor.setMap(map.toUpperCase());
            return ResponseEntity.ok(MapEditorResponse.fromEditor(editorID, editor));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/setFrog")
    @Description("Sets the frog position on the map")
    ResponseEntity<MapEditorResponse> setFrog(String editorID, @RequestBody FrogDTO frog) {
        Optional<MapEditor> optional = repository.findEditorById(editorID);
        if (optional.isPresent()) {
            MapEditor editor = optional.get();
            if (editor.isOOB(frog.position())) {
                return ResponseEntity.ok(MapEditorResponse.fromEditor(editorID, editor));
            }
            if (editor.getTile(frog.position()) == TileType.FLOOR) {
                editor.setFrogPosition(frog.position());
                editor.setFrogDirection(frog.direction());
            }
            return ResponseEntity.ok(MapEditorResponse.fromEditor(editorID, editor));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/setFood")
    @Description("Sets the frog position on the map")
    ResponseEntity<MapEditorResponse> setFood(String editorID, @RequestBody MapEditorFoodRequest req) {
        Optional<MapEditor> optional = repository.findEditorById(editorID);
        if (optional.isPresent()) {
            MapEditor editor = optional.get();
            editor.setFood(req.position(), req.foodCount());
            return ResponseEntity.ok(MapEditorResponse.fromEditor(editorID, editor));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/load")
    @Description("Loads a map.")
    ResponseEntity<MapEditorResponse> loadMap(String editorID) {
        Optional<MapEditor> optional = repository.findEditorById(editorID);
        return optional.map(editor -> ResponseEntity.ok(MapEditorResponse.fromEditor(editorID, editor)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
