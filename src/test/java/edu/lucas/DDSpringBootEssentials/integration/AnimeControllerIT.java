package edu.lucas.DDSpringBootEssentials.integration;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import edu.lucas.DDSpringBootEssentials.repository.MovieRepository;
import edu.lucas.DDSpringBootEssentials.util.MovieCreator;
import edu.lucas.DDSpringBootEssentials.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;

/**
 * Classe para testes de integração <br>
 * <b>@SpringBootTest</b>: indica uma classe de teste. WebEnvironment troca a porta onde é executado o teste.
 * <b>@AutoConfigureTestDatabase</b>: indica que usaremos um banco de testes.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
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
}
