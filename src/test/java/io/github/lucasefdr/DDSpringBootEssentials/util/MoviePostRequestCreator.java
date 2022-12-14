package io.github.lucasefdr.DDSpringBootEssentials.util;

import io.github.lucasefdr.DDSpringBootEssentials.requests.MoviePostRequest;

public class MoviePostRequestCreator {

    public static MoviePostRequest createMoviePostRequestBody() {
        return MoviePostRequest.builder()
                .name(MovieCreator.createMovieToBeSaved().getName())
                .build();
    }
}
