package edu.lucas.DDSpringBootEssentials.client;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Movie> entity = new RestTemplate().getForEntity("http://localhost:8080/movies/{id}", Movie.class, 1);
        log.info(entity);

        Movie object = new RestTemplate().getForObject("http://localhost:8080/movies/{id}", Movie.class, 1);
        log.info(object);

        Movie[] movies = new RestTemplate().getForObject("http://localhost:8080/movies/all", Movie[].class);
        log.info(Arrays.toString(movies));

        //@formatter:off
        ResponseEntity<List<Movie>> exchange = new RestTemplate().exchange("http://localhost:8080/movies/all", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        //@formatter:on
        log.info(exchange.getBody());
    }
}
