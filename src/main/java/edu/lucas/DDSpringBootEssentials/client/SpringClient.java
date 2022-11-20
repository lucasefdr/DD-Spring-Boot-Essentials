package edu.lucas.DDSpringBootEssentials.client;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        /*ResponseEntity<Movie> entity = new RestTemplate().getForEntity("http://localhost:8080/movies/{id}", Movie.class, 1);
        log.info(entity);

        Movie object = new RestTemplate().getForObject("http://localhost:8080/movies/{id}", Movie.class, 1);
        log.info(object);

        Movie[] movies = new RestTemplate().getForObject("http://localhost:8080/movies/all", Movie[].class);
        log.info(Arrays.toString(movies));

        //@formatter:off
        ResponseEntity<List<Movie>> exchange = new RestTemplate().exchange("http://localhost:8080/movies/all", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        //@formatter:on
        log.info(exchange.getBody());

        Movie sextaFeira13 = Movie.builder().name("Sexta-Feira 13").build();
        ResponseEntity<Movie> postEntity = new RestTemplate().postForEntity("http://localhost:8080/movies", sextaFeira13, Movie.class);
        log.info(postEntity);

        Movie aHoraDoPesadelo = new RestTemplate().postForObject("http://localhost:8080/movies", Movie.builder().name("A hora do Pesadelo").build(), Movie.class);
        log.info(aHoraDoPesadelo);

        ResponseEntity<Movie> aCasaDeCera = new RestTemplate().exchange("http://localhost:8080/movies", HttpMethod.POST, new HttpEntity<>(Movie.builder().name("A casa de cera").build(), createJsonHeader()), Movie.class);
        log.info(aCasaDeCera);

        ResponseEntity<Void> exchangeDelete = new RestTemplate().exchange("http://localhost:8080/movies/{1}", HttpMethod.DELETE, null, Void.class, 20);
        log.info(exchangeDelete);*/

        // exchange(url, method, request, class)
        ResponseEntity<Void> exchangePut = new RestTemplate().exchange("http://localhost:8080/movies", HttpMethod.PUT, new HttpEntity<>(Movie.builder().id(19L).name("O Exorcista").build()), Void.class);
        log.info(exchangePut);
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
