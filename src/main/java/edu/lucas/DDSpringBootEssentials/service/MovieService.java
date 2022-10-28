package edu.lucas.DDSpringBootEssentials.service;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import edu.lucas.DDSpringBootEssentials.exception.BadRequestException;
import edu.lucas.DDSpringBootEssentials.mapper.MovieMapper;
import edu.lucas.DDSpringBootEssentials.repository.MovieRepository;
import edu.lucas.DDSpringBootEssentials.requests.MoviePostRequest;
import edu.lucas.DDSpringBootEssentials.requests.MoviePutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// classe responsável pela regra de negócio
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    public Movie findById(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Movie not found."));
    }

    // rollback para exceções
    @Transactional(rollbackFor = Exception.class)
    public Movie save(MoviePostRequest moviePostRequest) {
        // Movie movie = Movie.builder().name(moviePostRequest.getName()).build();
        return movieRepository.save(MovieMapper.INSTANCE.toMovie(moviePostRequest));
    }

    public void delete(long id) {
        movieRepository.delete(findById(id));
    }

    public void replace(MoviePutRequest moviePutRequest) {
        Movie savedMovie = findById(moviePutRequest.getId());
        Movie movie = movieRepository.save(MovieMapper.INSTANCE.toMovie(moviePutRequest));
        movie.setId(savedMovie.getId());

        /*
        Movie movie = Movie.builder()
                .id(savedMovie.getId())
                .name(moviePutRequest.getName())
                .build();
        */
    }
}
