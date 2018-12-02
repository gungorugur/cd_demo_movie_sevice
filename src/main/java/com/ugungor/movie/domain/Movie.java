package com.ugungor.movie.domain;

public class Movie {

    private Integer id;
    private String name;
    private String description;
    private Double rating;

    private Movie(Integer id, String name, String description, Double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public static Movie create(Integer id, String name, String description, Double rating) {
        return new Movie(id, name, description, rating);
    }

    public Integer getId() {
        return id;
    }
}
