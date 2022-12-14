package io.github.lucasefdr.DDSpringBootEssentials.integration;

import io.github.lucasefdr.DDSpringBootEssentials.domain.Movie;
import io.github.lucasefdr.DDSpringBootEssentials.repository.MovieRepository;
import io.github.lucasefdr.DDSpringBootEssentials.requests.MoviePostRequest;
import io.github.lucasefdr.DDSpringBootEssentials.util.MovieCreator;
import io.github.lucasefdr.DDSpringBootEssentials.util.MoviePostRequestCreator;
import io.github.lucasefdr.DDSpringBootEssentials.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

/**
 * Classe para testes de integração <br>
 * <b>@SpringBootTest</b>: indica uma classe de teste. WebEnvironment troca a porta onde é executado o teste.
 * <b>@AutoConfigureTestDatabase</b>: indica que usaremos um banco de testes.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode =  DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AnimeControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @LocalServerPort // serve para pegar a porta utilizada no momento que a aplicação rodar
    private int port;

    @Test
    @DisplayName("list returns list of movies inside page object when successful")
    void list_ReturnsListOfMoviesInsidePageObject_WhenSuccessful() {
        Movie savedMovie = movieRepository.save(MovieCreator.createMovieToBeSaved());

        String expectedName = savedMovie.getName();

        PageableResponse<Movie> moviePage = testRestTemplate.exchange("/movies", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Movie>>() {
        }).getBody();

        Assertions.assertThat(moviePage).isNotNull();

        Assertions.assertThat(moviePage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(moviePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAll returns list of movies when successful")
    void listAll_ReturnsListOfMovies_WhenSuccessful() {
        Movie savedMovie = movieRepository.save(MovieCreator.createMovieToBeSaved());

        String expectedName = savedMovie.getName();

        List<Movie> movies = testRestTemplate.exchange("/movies/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>() {
        }).getBody();

        Assertions.assertThat(movies).isNotNull().isNotEmpty().hasSize(1);

        Assertions.assertThat(movies.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns movie when successful")
    void findById_ReturnsMovie_WhenSuccessful() {
        Movie savedMovie = movieRepository.save(MovieCreator.createMovieToBeSaved());
        Long expectedId = savedMovie.getId();

        Movie movie = testRestTemplate.getForObject("/movies/{id}", Movie.class, expectedId);

        // Asserção de que a lista não é nula, nem vazia e contém 1 elemento
        Assertions.assertThat(savedMovie).isNotNull();

        // Asserção de que o elemento que contém na lista é igual ao que foi declarado
        Assertions.assertThat(savedMovie.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of movies when successful")
    void findByName_ReturnsListOfMovies_WhenSuccessful() {
        // Criando e salvando um Movie
        Movie savedMovie = movieRepository.save(MovieCreator.createMovieToBeSaved());

        // Pegando o atributo name do Movie salvo
        String expectedName = savedMovie.getName();

        String url = String.format("/movies/find?name=%s", expectedName);

        List<Movie> movies = testRestTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>() {
        }).getBody();

        // Asserção de que a lista não é nula, nem vazia e contém 1 elemento
        Assertions.assertThat(movies).isNotNull().isNotEmpty().hasSize(1);

        // Asserção de que o elemento que contém na lista é igual ao que foi declarado
        Assertions.assertThat(movies.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of movies when movie is not found")
    void findByName_ReturnsEmptyListOfMovies_WhenSMovieIsNotFound() {
        List<Movie> movies = testRestTemplate.exchange("/movies/find?name=movieExample", HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>() {
        }).getBody();

        // Asserção de que a lista não é nula, nem vazia e contém 1 elemento
        Assertions.assertThat(movies).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("save returns movie when successful")
    void save_ReturnsMovie_WhenSuccessful() {
        MoviePostRequest moviePostRequest = MoviePostRequestCreator.createMoviePostRequestBody();

        ResponseEntity<Movie> movieResponseEntity = testRestTemplate.postForEntity("/movies", moviePostRequest, Movie.class);

        Assertions.assertThat(movieResponseEntity).isNotNull();
        Assertions.assertThat(movieResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(movieResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(movieResponseEntity.getBody().getId()).isNotNull();
    }

    @Test
    @DisplayName("replace updates movie when successful")
    void replace_UpdatesMovie_WhenSuccessful() {
        Movie savedMovie = movieRepository.save(MovieCreator.createMovieToBeSaved());
        savedMovie.setName("new name");


        ResponseEntity<Void> movieResponseEntity = testRestTemplate.exchange("/movies", HttpMethod.PUT, new HttpEntity<>(savedMovie), Void.class);

        Assertions.assertThat(movieResponseEntity).isNotNull();
        Assertions.assertThat(movieResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes movie when successful")
    void delete_RemovesMovie_WhenSuccessful() {
        Movie savedMovie = movieRepository.save(MovieCreator.createMovieToBeSaved());

        ResponseEntity<Void> movieResponseEntity = testRestTemplate.exchange("/movies/{id}", HttpMethod.DELETE, null, Void.class, savedMovie.getId());

        Assertions.assertThat(movieResponseEntity).isNotNull();
        Assertions.assertThat(movieResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
