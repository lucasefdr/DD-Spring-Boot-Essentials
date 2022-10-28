package edu.lucas.DDSpringBootEssentials.service;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// classe responsável pela regra de negócio
@Service
public class MovieService {
    private static final List<Movie> movies;

    static {
        movies = new ArrayList<>(List.of(
                new Movie(1L, "O Senhor dos Anéis"),
                new Movie(2L, "Batman"),
                new Movie(3L, "Harry Potter")));
    }

    public List<Movie> listAll() {
        return movies;
    }

    public Movie findById(long id) {
        return movies
                .stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie not found."));
    }

    public Movie save(Movie movie) {
        movie.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        movies.add(movie);
        return movie;
    }

    public void delete(long id) {
        movies.remove(findById(id));
    }

    public void replace(Movie movie) {
        delete(movie.getId());
        movies.add(movie);
    }
}
