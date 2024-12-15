package dev.sanket.myApplication.service;

import dev.sanket.myApplication.model.Joke;
import dev.sanket.myApplication.repository.JokeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JokeService {
    private final RestClient jokeRestClient;
    private final JokeRepository jokeRepository;

    public Joke getJoke() {
        Joke joke = jokeRestClient.get()
                .uri("/random_joke")
                .retrieve()
                .body(Joke.class);
        return jokeRepository.existsById(joke.getId()) ? joke : jokeRepository.save(joke);
    }

    public List<Joke> getJokes() {
        return jokeRepository.findAll();
    }

    public List<Joke> saveJokes(List<Joke> jokes) {
        return jokeRepository.saveAll(jokes);
    }
}
