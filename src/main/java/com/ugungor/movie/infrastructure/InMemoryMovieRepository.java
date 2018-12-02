package com.ugungor.movie.infrastructure;

import com.ugungor.movie.domain.Movie;
import com.ugungor.movie.domain.MovieRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InMemoryMovieRepository implements MovieRepository {

    private final List<Movie> db;

    public InMemoryMovieRepository() {

        this.db = Arrays.asList(
                Movie.create(1, "Goodfellas (1990)", "Lorem ipsum dolor sit amet", 9.7),
                Movie.create(2, "The Usual Suspects (1995)", "Lorem ipsum dolor sit amet", 9.6),
                Movie.create(3, "The Matrix (1999)", "Lorem ipsum dolor sit amet", 8.8),
                Movie.create(4, "Saving Private Ryan (1998)", "Lorem ipsum dolor sit amet", 8.5),
                Movie.create(5, "American History X (1998)", "Lorem ipsum dolor sit amet", 8.5)
        );
    }

    @Override
    public Optional<Movie> findById(Integer id) {

        return db.stream().filter(movie -> movie.getId().equals(id)).findFirst();
    }

    @Override
    public List<Movie> findAll() {
        return this.db;
    }
}
