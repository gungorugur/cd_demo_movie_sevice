package com.ugungor.movie.domain;

public class MovieNotFoundException extends RuntimeException {

    private MovieNotFoundException(String message) {
        super(message);
    }

    public static MovieNotFoundException create(String message) {
        return new MovieNotFoundException(message);
    }
}
