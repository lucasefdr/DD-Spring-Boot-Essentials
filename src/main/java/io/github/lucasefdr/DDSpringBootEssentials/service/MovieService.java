package io.github.lucasefdr.DDSpringBootEssentials.service;

import io.github.lucasefdr.DDSpringBootEssentials.domain.Movie;
import io.github.lucasefdr.DDSpringBootEssentials.exception.BadRequestException;
import io.github.lucasefdr.DDSpringBootEssentials.mapper.MovieMapper;
import io.github.lucasefdr.DDSpringBootEssentials.repository.MovieRepository;
import io.github.lucasefdr.DDSpringBootEssentials.requests.MoviePostRequest;
import io.github.lucasefdr.DDSpringBootEssentials.requests.MoviePutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * As classes de <strong>serviços</strong> são responsáveis pela <strong>regra de negócio da aplicação</strong>. São anotadas com a annotation <strong>@Service</strong> <br>
 */
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Page<Movie> listAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public List<Movie> listAllNonPageable() {
        return movieRepository.findAll();
    }

    public List<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new BadRequestException("Movie not found."));
    }

    // @Transactional para indicar que é uma transação
    @Transactional(rollbackFor = Exception.class)
    public Movie save(MoviePostRequest moviePostRequest) {
        // Movie movie = Movie.builder().name(moviePostRequest.getName()).build();
        return movieRepository.save(MovieMapper.INSTANCE.toMovie(moviePostRequest));
    }

    @Transactional
    public void delete(Long id) {
        movieRepository.delete(findById(id));
    }

    @Transactional
    public void replace(MoviePutRequest moviePutRequest) {
        Movie savedMovie = findById(moviePutRequest.getId());
        Movie movie = movieRepository.save(MovieMapper.INSTANCE.toMovie(moviePutRequest));
        movie.setId(savedMovie.getId());

        /*Movie movie = Movie.builder()
                .id(savedMovie.getId())
                .name(moviePutRequest.getName())
                .build();*/

    }
}
