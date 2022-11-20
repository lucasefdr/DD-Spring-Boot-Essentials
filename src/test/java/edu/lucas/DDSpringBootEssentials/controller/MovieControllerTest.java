package edu.lucas.DDSpringBootEssentials.controller;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import edu.lucas.DDSpringBootEssentials.requests.MoviePostRequest;
import edu.lucas.DDSpringBootEssentials.requests.MoviePutRequest;
import edu.lucas.DDSpringBootEssentials.service.MovieService;
import edu.lucas.DDSpringBootEssentials.util.MovieCreator;
import edu.lucas.DDSpringBootEssentials.util.MoviePostRequestCreator;
import edu.lucas.DDSpringBootEssentials.util.MoviePutRequestCreator;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;


// @SpringBootTest
@ExtendWith(SpringExtension.class)
class MovieControllerTest {

    // mockito
    @InjectMocks // Utilizar quando quiser testar a classe em si
    private MovieController movieController;

    @Mock // Utilizar para todas as classes utilizadas no @InjectMocks
    private MovieService movieServiceMock;

    // Antes de qualquer um
    @BeforeEach
    void setUp() {
        PageImpl<Movie> moviePage = new PageImpl<>(List.of(MovieCreator.createValidMovie()));

        // Mockito.QuandoChamar(Service).Retorne(PageImpl<>)
        BDDMockito.when(movieServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(moviePage);

        BDDMockito.when(movieServiceMock.listAllNonPageable())
                .thenReturn(List.of(MovieCreator.createValidMovie()));

        // ArgumentMatchers.anyLong() -> qualquer valor que passar
        BDDMockito.when(movieServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(MovieCreator.createValidMovie());

        BDDMockito.when(movieServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(MovieCreator.createValidMovie()));

        BDDMockito.when(movieServiceMock.save(ArgumentMatchers.any(MoviePostRequest.class)))
                .thenReturn(MovieCreator.createValidMovie());

        // Esse replace retorna void
        BDDMockito.doNothing().when(movieServiceMock).replace(ArgumentMatchers.any(MoviePutRequest.class));

        BDDMockito.doNothing().when(movieServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("list returns list of movies inside page object when successful")
    void list_ReturnsListOfMoviesInsidePageObject_WhenSuccessful() {
        String expectedName = MovieCreator.createValidMovie().getName();
        Page<Movie> moviePage = movieController.list(null).getBody();

        Assertions.assertThat(moviePage).isNotNull();

        Assertions.assertThat(moviePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(moviePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAll returns list of movies when successful")
    void listAll_ReturnsListOfMovies_WhenSuccessful() {
        String expectedName = MovieCreator.createValidMovie().getName();
        List<Movie> movies = movieController.listAll().getBody();

        // Asserção de que a lista não é nula, nem vazia e contém 1 elemento
        Assertions.assertThat(movies)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        // Asserção de que o elemento que contém na lista é igual ao que foi declarado
        Assertions.assertThat(movies.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns movie when successful")
    void findById_ReturnsMovie_WhenSuccessful() {
        Long expectedId = MovieCreator.createValidMovie().getId();
        Movie movie = movieController.findById(1).getBody();

        // Asserção de que a lista não é nula, nem vazia e contém 1 elemento
        Assertions.assertThat(movie).isNotNull();

        // Asserção de que o elemento que contém na lista é igual ao que foi declarado
        Assertions.assertThat(movie.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of movies when successful")
    void findByName_ReturnsListOfMovies_WhenSuccessful() {
        String expectedName = MovieCreator.createValidMovie().getName();
        List<Movie> movies = movieController.findByName("Movie example").getBody();

        // Asserção de que a lista não é nula, nem vazia e contém 1 elemento
        Assertions.assertThat(movies)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        // Asserção de que o elemento que contém na lista é igual ao que foi declarado
        Assertions.assertThat(movies.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of movies when movie is not found")
    void findByName_ReturnsEmptyListOfMovies_WhenSMovieIsNotFound() {
        // Retorne uma lista vazia
        BDDMockito.when(movieServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Movie> movies = movieController.findByName("Movie example").getBody();

        Assertions.assertThat(movies)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns movie when successful")
    void save_ReturnsMovie_WhenSuccessful() {
        Movie movie = movieController.save(MoviePostRequestCreator.createMoviePostRequestBody()).getBody();

        Assertions.assertThat(movie).isNotNull().isEqualTo(MovieCreator.createValidMovie());
    }

    @Test
    @DisplayName("replace updates movie when successful")
    void replace_UpdatesMovie_WhenSuccessful() {

        Assertions.assertThatCode(() -> movieController.replace(MoviePutRequestCreator.createMoviePutRequestBody())
                        .getBody())
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = movieController.replace(MoviePutRequestCreator.createMoviePutRequestBody());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes movie when successful")
    void delete_RemovesMovie_WhenSuccessful() {

        Assertions.assertThatCode(() -> movieController.delete(1)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = movieController.delete(1);

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}