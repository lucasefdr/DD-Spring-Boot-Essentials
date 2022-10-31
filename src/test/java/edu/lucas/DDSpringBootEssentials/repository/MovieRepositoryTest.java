package edu.lucas.DDSpringBootEssentials.repository;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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
        Movie movieSaved = this.movieRepository.save(movieToBeSaved);

        // Verifique que o movieSaved não é null
        Assertions.assertThat(movieSaved).isNotNull();

        // Verifique que o id do movieSaved não é null
        Assertions.assertThat(movieSaved.getId()).isNotNull();

        // Verifique que o nome do movieSaved é igual a do movieToBeSaved
        Assertions.assertThat(movieSaved.getName()).isEqualTo(movieToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates Movie when successful")
    void save_UpdatesMovie_WhenSuccessful() {
        Movie movieToBeSaved = createMovie();
        Movie movieSaved = this.movieRepository.save(movieToBeSaved);

        movieSaved.setName("O Rei Leão");

        Movie movieUpdated = this.movieRepository.save(movieSaved);

        // Verifique que o movieSaved não é null
        Assertions.assertThat(movieUpdated).isNotNull();

        // Verifique que o id do movieSaved não é null
        Assertions.assertThat(movieUpdated.getId()).isNotNull();

        // Verifique que o nome do movieSaved é igual a do movieToBeSaved
        Assertions.assertThat(movieUpdated.getName()).isEqualTo(movieSaved.getName());
    }

    @Test
    @DisplayName("Delete removes Movie when successful")
    void delete_RemovesMovie_WhenSuccessful() {
        Movie movieToBeSaved = createMovie();
        Movie movieSaved = this.movieRepository.save(movieToBeSaved);

        this.movieRepository.delete(movieSaved);

        Optional<Movie> movieOptional = this.movieRepository.findById(movieSaved.getId());

        // Verifique que o movieOptional está vazio (deletado)
        Assertions.assertThat(movieOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by name returns list of movies when successful")
    void findByName_ReturnsMovie_WhenSuccessful() {
        Movie movieToBeSaved = createMovie();
        Movie movieSaved = this.movieRepository.save(movieToBeSaved);

        String name = movieSaved.getName();

        List<Movie> movies = this.movieRepository.findByName(name);

        // Verifique que movies não está vazio
        Assertions.assertThat(movies).isNotEmpty();

        // Verifique que movies contém o movieSaved
        Assertions.assertThat(movies).contains(movieSaved);
    }

    @Test
    @DisplayName("Find by name returns empty list when no movie is found")
    void findByName_ReturnsEmptyList_WhenMovieIsNotFound() {
        List<Movie> movies = this.movieRepository.findByName("example");

        // Verifique que movies está vazio
        Assertions.assertThat(movies).isEmpty();
    }

    // Criando um objeto para os testes
    private Movie createMovie() {
        return Movie.builder().name("Além da vida").build();
    }
}