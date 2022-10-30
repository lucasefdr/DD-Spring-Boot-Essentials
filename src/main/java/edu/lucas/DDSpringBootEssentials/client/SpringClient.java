package edu.lucas.DDSpringBootEssentials.client;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Movie> entity = new RestTemplate().getForEntity("http://localhost:8080/movies/{id}", Movie.class, 1);
        log.info(entity);

        Movie object = new RestTemplate().getForObject("http://localhost:8080/movies/{id}", Movie.class, 1);
        log.info(object);
    }
}
