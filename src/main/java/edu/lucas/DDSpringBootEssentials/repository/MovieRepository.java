package edu.lucas.DDSpringBootEssentials.repository;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * As ‘interfaces’ de <strong>repositórios</strong> são responsáveis pela <strong>conexão com o banco de dados</strong>. São anotadas com a annotation <strong>@Repository</strong> <br>
 * A ‘interface’ <strong>JpaRepository</strong> implementa várias queries internamente.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String name);
}
