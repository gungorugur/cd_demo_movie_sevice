package com.ugungor.movie.interfaces;

import java.util.Optional;

public class Health {

    private String message;
    private String version;

    private Health(String message) {

        this.message = message;
        this.version = Optional.ofNullable(System.getenv("VERSION")).orElse("0");
    }

    public static Health create(String message) {
        return new Health(message);
    }
}
