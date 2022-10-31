package edu.lucas.DDSpringBootEssentials.repository;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

// Classe de testes unitários
@DataJpaTest
@DisplayName("Tests for Movie Repository")
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    // Método de teste
    // Convenção para nome do teste: método, o que é pra fazer, o que esperar
    @Test
    @DisplayName("Save creates Movie when successful")
    void save_PersistMovie_WhenSuccessful() {
        Movie movieToBeSaved = createMovie();
        Movie savedMovie = this.movieRepository.save(movieToBeSaved);

        // Verifique que o savedMovie não é null
        Assertions.assertThat(savedMovie).isNotNull();

        // Verifique que o id do savedMovie não é null
        Assertions.assertThat(savedMovie.getId()).isNotNull();

        // Verifique que o nome do savedMovie é igual a do movieToBeSaved
        Assertions.assertThat(savedMovie.getName()).isEqualTo(movieToBeSaved.getName());
    }

    // Criando um objeto para os testes
    private Movie createMovie() {
        return Movie.builder().name("Além da vida").build();
    }
}