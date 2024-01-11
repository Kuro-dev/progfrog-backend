package scriptenginetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kurodev.progfrog.ProgFrogApplication;
import org.kurodev.progfrog.api.repository.SimpleGameRepository;
import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.game.map.ProgFrogLevel;
import org.kurodev.progfrog.game.util.Coordinate;
import org.kurodev.progfrog.game.util.Direction;
import org.kurodev.progfrog.script.JavaScriptManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.ScriptException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = ProgFrogApplication.class)
public class ScriptEngineTest {
    ProgFrogGame game;
    @Autowired
    private SimpleGameRepository repository;


    @BeforeEach
    public void prepare() {
        String map = """
                0XXXXXXXXX
                XXXXXXXXXX
                0XXXXXXXXX
                0XXXXXXXXX
                XXXXXXXXXX
                XXXXXXXXXX
                XXXXX5XXXX
                XXXXXXXXXX
                XXXXXXXXXX
                XXXXXXXXXX
                """;
        var level = ProgFrogLevel.fromString(map, "\n");
        game = new ProgFrogGame(level, Direction.SOUTH, Coordinate.of(0, 0));
    }

    @Test
    void contextLoads() throws ScriptException {
        String id = repository.storeGame(game);
        JavaScriptManager manager = new JavaScriptManager(repository.findGameByID(id).orElseThrow());
        manager.compile("""
                public void main() {
                    move();
                }
                """);
        assertThrows(ScriptException.class, manager::execute);
        manager.compile("""
                public void main() {
                    eat();
                }
                """);
        assertThrows(ScriptException.class, manager::execute);
        manager.compile("""
                public void main() {
                move();
                eat();
                }
                """);
        String mapUpdated = """
                0XXXXXXXXX
                1XXXXXXXXX
                0XXXXXXXXX
                0XXXXXXXXX
                XXXXXXXXXX
                XXXXXXXXXX
                XXXXX5XXXX
                XXXXXXXXXX
                XXXXXXXXXX
                XXXXXXXXXX
                """;
        manager.execute();
        assertEquals(2, manager.getResult().getCallStack().size());
    }
}
