package edu.lucas.DDSpringBootEssentials.controller;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import edu.lucas.DDSpringBootEssentials.requests.MoviePostRequest;
import edu.lucas.DDSpringBootEssentials.requests.MoviePutRequest;
import edu.lucas.DDSpringBootEssentials.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * <b>@RestController</b>: controlador REST <br>
 * <b>@RequestMapping</b>: mapeamento da requisição <br>
 * <b>@Log4j2</b>: log no terminal através do log.info <br>
 * <b>@AllArgsConstructor</b>: annotation para criar construtor de todas as classes declaradas <br>
 * <b>@RequiredArgsConstructor</b>: annotation para criar construtor de todas as classes constantes (final)
 */
@RestController
@RequestMapping("movies")
@Log4j2
// @AllArgsConstructor
@RequiredArgsConstructor
public class MovieController {

    /**
     * <b>@Autowired</b>: injeção de dependência
     */
    // @Autowired
    // private final DateUtil dateUtil;
    private final MovieService movieService;

    /**
     * Endpoint: <b>localhost:8080/movies</b> <br>
     * Método: <b>GET</b> <br>
     * <b>@ResponseEntity</b>: entidade de resposta
     */
    @GetMapping
    public ResponseEntity<List<Movie>> listAll() { // ResponseEntity => Entidade de Resposta -> Uma List de Movie
        // log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())); // Log no terminal a hora que executa o projeto

        return ResponseEntity.ok(movieService.listAll());
        // return new ResponseEntity<>(movieService.listAll(), HttpStatus.OK);
    }

    /**
     * Endpoint: <b>localhost:8080/movies/{id}</b> <br>
     * Método: <b>GET</b> <br>
     * <b>@PathVariable</b>: variável de caminho passada na URL
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    /**
     * Endpoint: <b>localhost:8080/movies/{id}</b> <br>
     * Método: <b>GET</b> <br>
     * <b>@PathVariable</b>: variável de caminho passada na URL
     */
    @GetMapping(path = "/find")
    public ResponseEntity<List<Movie>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(movieService.findByName(name));
    }

    /**
     * Endpoint: <b>localhost:8080/movies</b> <br>
     * Método: <b>POST</b> <br>
     * <b>@RequestBody</b>: requisita de um body para tratar com o jackson. <br>
     * <b>@ResponseStatus</b>: padrão de resposta do endpoint. <br>
     * <b>jackson</b>: faz o mapeamento do JSON se o mesmo conter as propriedades idênticas a classe
     */
    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Movie> save(@RequestBody @Valid MoviePostRequest moviePostRequest) {
        return new ResponseEntity<>(movieService.save(moviePostRequest), HttpStatus.CREATED);
    }

    /**
     * Endpoint: <b>localhost:8080/movies/{id}</b> <br>
     * Método: <b>DELETE</b> <br>
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint: <b>localhost:8080/movies</b> <br>
     * Método: <b>POST</b> <br>
     */
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody MoviePutRequest moviePutRequest) {
        movieService.replace(moviePutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}