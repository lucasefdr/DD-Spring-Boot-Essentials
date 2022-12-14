package io.github.lucasefdr.DDSpringBootEssentials.util;

import io.github.lucasefdr.DDSpringBootEssentials.domain.Movie;

public class MovieCreator {

    public static Movie createMovieToBeSaved() {
        return Movie.builder()
                .name("Além da vida")
                .build();
    }

    public static Movie createValidMovie() {
        return Movie.builder()
                .id(1L)
                .name("Além da vida")
                .build();
    }

    public static Movie createValidUpdatedMovie() {
        return Movie.builder()
                .name("A hora do pesadelo")
                .id(1L)
                .build();
    }
}
