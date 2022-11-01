package edu.lucas.DDSpringBootEssentials.controller;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import edu.lucas.DDSpringBootEssentials.service.MovieService;
import edu.lucas.DDSpringBootEssentials.util.MovieCreator;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        BDDMockito.when(movieServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(moviePage);
    }

    @Test
    @DisplayName("List returns list of movies inside page object when successful")
    void list_ReturnsListOfMoviesInsidePageObject_WhenSuccessful() {
        String expectedName = MovieCreator.createValidMovie().getName();
        Page<Movie> moviePage = movieController.listAll(null).getBody();

        Assertions.assertThat(moviePage).isNotNull();

        Assertions.assertThat(moviePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(moviePage.toList().get(0).getName()).isEqualTo(expectedName);
    }


}