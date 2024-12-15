package dev.sanket.myApplication.repository;

import dev.sanket.myApplication.model.Joke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JokeRepository extends JpaRepository<Joke, Long> {
}
