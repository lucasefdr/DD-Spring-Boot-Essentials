package edu.lucas.DDSpringBootEssentials.repository;

import edu.lucas.DDSpringBootEssentials.domain.Movie;

import java.util.List;

// Interface de conexão com o banco de dados
public interface MovieRepository {
    List<Movie> listAll();
}
