package org.kurodev.progfrog.api;

import jakarta.annotation.Nullable;
import org.kurodev.progfrog.api.dto.editor.MapEditorDrawRequest;
import org.kurodev.progfrog.api.dto.editor.MapEditorResponse;
import org.kurodev.progfrog.api.repository.GameRepository;
import org.kurodev.progfrog.game.map.MapEditor;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(new MapEditorResponse(id, editor.toString()));
    }

    @PostMapping("/draw")
    @Description("Draws a tile on a map.")
    ResponseEntity<MapEditorResponse> draw(String editorID, @RequestBody List<MapEditorDrawRequest> drawRequests) {
        var editor = repository.findEditorById(editorID);
        if (editor.isPresent()) {
            for (MapEditorDrawRequest req : drawRequests) {
                editor.get().setTile(req.pos(), req.type());
            }
            return ResponseEntity.ok(new MapEditorResponse(editorID, editor.get().toString()));
        }
        return ResponseEntity.notFound().build();
    }

}
