package dev.sanket.myApplication.controller;

import dev.sanket.myApplication.model.Joke;
import dev.sanket.myApplication.service.JokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyController {
    private final JokeService jokeService;

    @GetMapping("/joke")
    public ResponseEntity<Joke> joke() {
        return ResponseEntity.ok(jokeService.getJoke());
    }

    @GetMapping("/Alljokes")
    public ResponseEntity<List<Joke>> jokes() {
        return ResponseEntity.ok(jokeService.getJokes());
    }

    @PostMapping("/save")
    public ResponseEntity<List<Joke>> saveJokes(@RequestBody List<Joke> jokes) {
        return ResponseEntity.ok(jokeService.saveJokes(jokes));
    }
}
