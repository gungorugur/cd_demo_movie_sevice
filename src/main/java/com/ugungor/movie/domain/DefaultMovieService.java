package com.ugungor.movie.domain;

import java.util.List;

public class DefaultMovieService implements MovieService {

    private MovieRepository movieRepository;

    public DefaultMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {

        final List<Movie> movies = movieRepository.findAll();

        if (movies.isEmpty()) throw MovieNotFoundException.create("No movies exist in the system!!");

        return movies;
    }

    @Override
    public Movie findById(Integer id) {

        return movieRepository
                .findById(id)
                .orElseThrow(() -> MovieNotFoundException.create("No movies exist in the system with given id:" + id));
    }
}
