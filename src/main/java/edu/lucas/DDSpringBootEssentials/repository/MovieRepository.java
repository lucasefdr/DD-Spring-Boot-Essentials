package edu.lucas.DDSpringBootEssentials.repository;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * As interfaces de <b>repositórios</b> são responsáveis pela <b>conexão com o banco de dados</b>. <br>
 * A interface <b>JpaRepository</b> implementa várias queries internamente.
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String name);
}
