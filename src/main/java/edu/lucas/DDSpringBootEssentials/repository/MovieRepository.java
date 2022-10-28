package edu.lucas.DDSpringBootEssentials.repository;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Interface de conexão com o banco de dados
// JpaRepository já implementa várias métodos HTTP internamente
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
