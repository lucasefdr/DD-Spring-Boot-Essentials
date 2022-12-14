package io.github.lucasefdr.DDSpringBootEssentials.repository;

import io.github.lucasefdr.DDSpringBootEssentials.domain.Movie;
import io.github.lucasefdr.DDSpringBootEssentials.util.MovieCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

// Classe de testes unitários
@DataJpaTest
@DisplayName("Tests for Movie Repository")
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    // Método de teste
    // Convenção para nome do teste: método, o que é para fazer, o que esperar
    @Test
    @DisplayName("Save creates Movie when successful")
    void save_PersistMovie_WhenSuccessful() {
        Movie movieToBeSaved = MovieCreator.createMovieToBeSaved();
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
        Movie movieToBeSaved = MovieCreator.createMovieToBeSaved();
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
        Movie movieToBeSaved = MovieCreator.createMovieToBeSaved();
        Movie movieSaved = this.movieRepository.save(movieToBeSaved);

        this.movieRepository.delete(movieSaved);

        Optional<Movie> movieOptional = this.movieRepository.findById(movieSaved.getId());

        // Verifique que o movieOptional está vazio (deletado)
        Assertions.assertThat(movieOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by name returns list of movies when successful")
    void findByName_ReturnsMovie_WhenSuccessful() {
        Movie movieToBeSaved = MovieCreator.createMovieToBeSaved();
        Movie movieSaved = this.movieRepository.save(movieToBeSaved);

        String name = movieSaved.getName();

        List<Movie> movies = this.movieRepository.findByName(name);

        // Verifique que movies não está vazio e se contém o movieSaved
        Assertions.assertThat(movies).isNotEmpty().contains(movieSaved);
    }

    @Test
    @DisplayName("Find by name returns empty list when no movie is found")
    void findByName_ReturnsEmptyList_WhenMovieIsNotFound() {
        List<Movie> movies = this.movieRepository.findByName("example");

        // Verifique que movies está vazio
        Assertions.assertThat(movies).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowConstraintViolationException_WhenNameIsEmpty() {
        Movie movie = new Movie();
        /*Assertions.assertThatThrownBy(() -> this.movieRepository.save(movie))
                .isInstanceOf(ConstraintViolationException.class);*/

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.movieRepository.save(movie))
                .withMessageContaining("The movie name cannot be empty");
    }

    // Criando um objeto para os testes

}