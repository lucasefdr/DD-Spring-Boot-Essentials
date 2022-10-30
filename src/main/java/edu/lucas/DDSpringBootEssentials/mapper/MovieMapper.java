package edu.lucas.DDSpringBootEssentials.mapper;

import edu.lucas.DDSpringBootEssentials.domain.Movie;
import edu.lucas.DDSpringBootEssentials.requests.MoviePostRequest;
import edu.lucas.DDSpringBootEssentials.requests.MoviePutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    public static final MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    // será convertido para movie
    public abstract Movie toMovie(MoviePostRequest moviePostRequest);

    public abstract Movie toMovie(MoviePutRequest moviePutRequest);


}
