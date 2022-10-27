package edu.lucas.DDSpringBootEssentials.controller;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import edu.lucas.DDSpringBootEssentials.service.MovieService;
import edu.lucas.DDSpringBootEssentials.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

// Controller
@RestController
@RequestMapping("movies")
@Log4j2 // log no terminal através do log.info
// @AllArgsConstructor // cria um construtor de todas as classes declaradas
@RequiredArgsConstructor // cria um construtor com as classes constantes (final)
public class MovieController {

    // @Autowired // Injeção de dependência
    private final DateUtil dateUtil;
    private final MovieService animeService;

    // localhost:8080/movies
    @GetMapping
    public List<Movie> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return animeService.listAll();
    }
}