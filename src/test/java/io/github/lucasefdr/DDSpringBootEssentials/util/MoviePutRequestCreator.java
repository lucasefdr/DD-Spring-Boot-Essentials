package io.github.lucasefdr.DDSpringBootEssentials.util;

import io.github.lucasefdr.DDSpringBootEssentials.requests.MoviePutRequest;

public class MoviePutRequestCreator {

    public static MoviePutRequest createMoviePutRequestBody() {
        return MoviePutRequest.builder()
                .id(MovieCreator.createValidUpdatedMovie().getId())
                .name(MovieCreator.createValidUpdatedMovie().getName())
                .build();
    }
}
