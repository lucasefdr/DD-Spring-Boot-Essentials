package io.github.lucasefdr.DDSpringBootEssentials.mapper;

import io.github.lucasefdr.DDSpringBootEssentials.domain.Movie;
import io.github.lucasefdr.DDSpringBootEssentials.requests.MoviePostRequest;
import io.github.lucasefdr.DDSpringBootEssentials.requests.MoviePutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    public static final MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    // será convertido para movie
    public abstract Movie toMovie(MoviePostRequest moviePostRequest);

    public abstract Movie toMovie(MoviePutRequest moviePutRequest);


}
