package edu.lucas.DDSpringBootEssentials.util;

import edu.lucas.DDSpringBootEssentials.requests.MoviePostRequest;

public class MoviePostRequestCreator {

    public static MoviePostRequest createMoviePostRequestBody() {
        return MoviePostRequest.builder()
                .name(MovieCreator.createMovieToBeSaved().getName())
                .build();
    }
}
