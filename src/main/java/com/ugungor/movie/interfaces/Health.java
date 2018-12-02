package com.ugungor.movie.interfaces;

public class Health {

    public String message;

    private Health(String message) {
        this.message = message;
    }

    public static Health create(String message) {
        return new Health(message);
    }
}
