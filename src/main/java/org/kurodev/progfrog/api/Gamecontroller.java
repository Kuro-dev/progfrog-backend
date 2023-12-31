package org.kurodev.progfrog.api;

import org.kurodev.progfrog.api.dto.CompileMapRequest;
import org.kurodev.progfrog.api.dto.CompileMapResponse;
import org.kurodev.progfrog.api.dto.CompileScriptRequest;
import org.kurodev.progfrog.api.repository.GameRepository;
import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.JavaScriptManager;
import org.kurodev.progfrog.script.ScriptResult;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;
import java.util.Optional;

@RestController("GameController")
public class Gamecontroller {

    private final GameRepository repository;

    public Gamecontroller(GameRepository repository) {

        this.repository = repository;
    }

    @PostMapping("/compileMap")
    @Description("Compiles the map. This is the first step to running the game.")
    public ResponseEntity<CompileMapResponse> compileMap(@RequestBody CompileMapRequest request) {
        ProgFrogGame game = request.toGame();
        String id = repository.storeGame(game);
        return ResponseEntity.ok(new CompileMapResponse(id));
    }

    @PostMapping("/compileScript")
    @Description("Compiles the script to be executed. May throw syntax errors.")
    public ResponseEntity<CompileMapResponse> compileScript(@RequestBody CompileScriptRequest request) throws ScriptException {
        Optional<ProgFrogGame> game = repository.findGameByID(request.mapID());
        if (game.isPresent()) {
            JavaScriptManager manager = new JavaScriptManager(game.get());
            manager.compile(request.script());
            String id = repository.storeScript(manager);
            return ResponseEntity.ok(new CompileMapResponse(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/execute")
    @Description("Executes the given script.")
    public ResponseEntity<ScriptResult> executeScript(@RequestBody String scriptId) {
        Optional<JavaScriptManager> game = repository.findScriptById(scriptId);
        if (game.isPresent()) {
            try {
                ScriptResult result = game.get().execute();
                return ResponseEntity.ok(result);
            } catch (ScriptException e) {
                return ResponseEntity.internalServerError().body(game.get().getResult());
            }
        }
        return ResponseEntity.notFound().build();
    }


}
