package org.kurodev.progfrog.api;

import org.kurodev.progfrog.api.dto.game.CompileScriptRequest;
import org.kurodev.progfrog.api.dto.game.ScriptResultDTO;
import org.kurodev.progfrog.api.repository.GameRepository;
import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.game.map.MapEditor;
import org.kurodev.progfrog.script.JavaScriptManager;
import org.kurodev.progfrog.script.ScriptResult;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;
import java.util.Optional;

@RestController("GameController")
@RequestMapping("/game")
public class GameController {

    private final GameRepository repository;

    public GameController(GameRepository repository) {

        this.repository = repository;
    }

    @PostMapping("/compileScript")
    @Description("Compiles the script to be executed. May throw syntax errors.")
    public ResponseEntity<ScriptResultDTO> compileScript(@RequestBody CompileScriptRequest request) throws ScriptException {
        Optional<MapEditor> map = repository.findEditorById(request.mapID());
        if (map.isPresent()) {
            ProgFrogGame game = map.get().toGame();
            JavaScriptManager manager = new JavaScriptManager(game);
            manager.compile(request.script());
            manager.execute();
            ScriptResult result = manager.getResult();
            String id = repository.storeScriptResult(result);
            return ResponseEntity.ok(ScriptResultDTO.of(request.mapID(), request.mapID(), result));
        }
        return ResponseEntity.notFound().build();
    }
}
