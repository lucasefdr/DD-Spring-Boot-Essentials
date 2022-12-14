package io.github.lucasefdr.DDSpringBootEssentials.service;

import io.github.lucasefdr.DDSpringBootEssentials.domain.Movie;
import io.github.lucasefdr.DDSpringBootEssentials.exception.BadRequestException;
import io.github.lucasefdr.DDSpringBootEssentials.repository.MovieRepository;
import io.github.lucasefdr.DDSpringBootEssentials.util.MovieCreator;
import io.github.lucasefdr.DDSpringBootEssentials.util.MoviePostRequestCreator;
import io.github.lucasefdr.DDSpringBootEssentials.util.MoviePutRequestCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class MovieServiceTest {


    // mockito
    @InjectMocks // Utilizar quando quiser testar a classe em si
    private MovieService movieService;

    @Mock // Utilizar para todas as classes utilizadas no @InjectMocks
    private MovieRepository movieRepositoryMock;

    // Antes de qualquer um
    @BeforeEach
    void setUp() {
        PageImpl<Movie> moviePage = new PageImpl<>(List.of(MovieCreator.createValidMovie()));

        // Mockito.QuandoChamar(Service).Retorne(PageImpl<>)
        BDDMockito.when(movieRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(moviePage);

        BDDMockito.when(movieRepositoryMock.findAll()).thenReturn(List.of(MovieCreator.createValidMovie()));

        // ArgumentMatchers.anyLong() -> qualquer valor que passar
        BDDMockito.when(movieRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(MovieCreator.createValidMovie()));

        BDDMockito.when(movieRepositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(List.of(MovieCreator.createValidMovie()));

        BDDMockito.when(movieRepositoryMock.save(ArgumentMatchers.any(Movie.class))).thenReturn(MovieCreator.createValidMovie());

        BDDMockito.doNothing().when(movieRepositoryMock).delete(ArgumentMatchers.any(Movie.class));
    }

    @Test
    @DisplayName("list returns list of movies inside page object when successful")
    void list_ReturnsListOfMoviesInsidePageObject_WhenSuccessful() {
        String expectedName = MovieCreator.createValidMovie().getName();
        Page<Movie> moviePage = movieService.listAll(PageRequest.of(1, 1));

        Assertions.assertThat(moviePage).isNotNull();

        Assertions.assertThat(moviePage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(moviePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAll returns list of movies when successful")
    void listAll_ReturnsListOfMovies_WhenSuccessful() {
        String expectedName = MovieCreator.createValidMovie().getName();
        List<Movie> movies = movieService.listAllNonPageable();

        // Asserção de que a lista não é nula, nem vazia e contém 1 elemento
        Assertions.assertThat(movies).isNotNull().isNotEmpty().hasSize(1);

        // Asserção de que o elemento que contém na lista é igual ao que foi declarado
        Assertions.assertThat(movies.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns movie when successful")
    void findById_ReturnsMovie_WhenSuccessful() {
        Long expectedId = MovieCreator.createValidMovie().getId();
        Movie movie = movieService.findById(1L);

        // Asserção de que a lista não é nula, nem vazia e contém 1 elemento
        Assertions.assertThat(movie).isNotNull();

        // Asserção de que o elemento que contém na lista é igual ao que foi declarado
        Assertions.assertThat(movie.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findById throws BadRequestException when movie is not found")
    void findById_Throws_BadRequestException_WhenMovieIsNotFound() {
        BDDMockito.when(movieRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> movieService.findById(1L));
    }

    @Test
    @DisplayName("findByName returns a list of movies when successful")
    void findByName_ReturnsListOfMovies_WhenSuccessful() {
        String expectedName = MovieCreator.createValidMovie().getName();
        List<Movie> movies = movieService.findByName("Movie example");

        // Asserção de que a lista não é nula, nem vazia e contém 1 elemento
        Assertions.assertThat(movies).isNotNull().isNotEmpty().hasSize(1);

        // Asserção de que o elemento que contém na lista é igual ao que foi declarado
        Assertions.assertThat(movies.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of movies when movie is not found")
    void findByName_ReturnsEmptyListOfMovies_WhenSMovieIsNotFound() {
        // Retorne uma lista vazia
        BDDMockito.when(movieRepositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(Collections.emptyList());

        List<Movie> movies = movieService.findByName("Movie example");

        Assertions.assertThat(movies).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("save returns movie when successful")
    void save_ReturnsMovie_WhenSuccessful() {
        Movie movie = movieService.save(MoviePostRequestCreator.createMoviePostRequestBody());

        Assertions.assertThat(movie).isNotNull().isEqualTo(MovieCreator.createValidMovie());
    }

    @Test
    @DisplayName("replace updates movie when successful")
    void replace_UpdatesMovie_WhenSuccessful() {

        Assertions.assertThatCode(() -> movieService.replace(MoviePutRequestCreator.createMoviePutRequestBody())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete removes movie when successful")
    void delete_RemovesMovie_WhenSuccessful() {

        Assertions.assertThatCode(() -> movieService.delete(1L)).doesNotThrowAnyException();
    }
}