package org.kurodev.progfrog.api;

import org.kurodev.progfrog.script.ProgFrogScriptParser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ScriptController {
@GetMapping("/blah")
    public void test(){
    new ProgFrogScriptParser();

    }
}
