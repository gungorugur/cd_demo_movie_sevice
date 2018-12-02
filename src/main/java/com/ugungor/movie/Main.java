package com.ugungor.movie;

import com.ugungor.movie.application.MovieController;
import com.ugungor.movie.domain.DefaultMovieService;
import com.ugungor.movie.domain.MovieRepository;
import com.ugungor.movie.domain.MovieService;
import com.ugungor.movie.infrastructure.InMemoryMovieRepository;

public class Main {

    public static void main(String[] args) {

        final MovieRepository movieRepository = new InMemoryMovieRepository();
        final MovieService movieService = new DefaultMovieService(movieRepository);
        final MovieController movieController = new MovieController(movieService);

        movieController.handleRoutes();
        movieController.handleExceptions();
    }
}
