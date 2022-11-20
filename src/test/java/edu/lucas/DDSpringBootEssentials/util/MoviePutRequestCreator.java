package edu.lucas.DDSpringBootEssentials.util;

import edu.lucas.DDSpringBootEssentials.requests.MoviePutRequest;

public class MoviePutRequestCreator {

    public static MoviePutRequest createMoviePutRequestBody() {
        return MoviePutRequest.builder()
                .id(MovieCreator.createValidUpdatedMovie().getId())
                .name(MovieCreator.createValidUpdatedMovie().getName())
                .build();
    }
}
