package io.github.lucasefdr.DDSpringBootEssentials.repository;

import io.github.lucasefdr.DDSpringBootEssentials.domain.Movie;
import io.github.lucasefdr.DDSpringBootEssentials.domain.UserAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * As ‘interfaces’ de <strong>repositórios</strong> são responsáveis pela <strong>conexão com o banco de dados</strong>. São anotadas com a annotation <strong>@Repository</strong> <br>
 * A ‘interface’ <strong>JpaRepository</strong> implementa várias queries internamente.
 */
@Repository
public interface UserAPIRepository extends JpaRepository<UserAPI, Long> {
    UserAPI findByUsername(String username);
}
