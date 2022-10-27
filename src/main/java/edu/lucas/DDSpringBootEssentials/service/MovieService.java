package edu.lucas.DDSpringBootEssentials.service;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

// classe responsável pela regra de negócio
@Service
public class MovieService {
    public List<Movie> listAll() {
        return List.of(new Movie(1L, "O Senhor dos Anéis"), new Movie(2L, "Batman"), new Movie(3L, "Harry Potter"));
    }
}
