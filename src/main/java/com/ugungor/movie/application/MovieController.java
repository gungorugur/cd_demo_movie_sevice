package com.ugungor.movie.application;

import com.ugungor.movie.domain.MovieNotFoundException;
import com.ugungor.movie.domain.MovieService;
import com.ugungor.movie.infrastructure.JsonTransformer;
import com.ugungor.movie.interfaces.ApiError;
import com.ugungor.movie.interfaces.Health;

import static spark.Spark.exception;
import static spark.Spark.get;

public class MovieController {

    private static final JsonTransformer JSON_TRANSFORMER = new JsonTransformer();

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public void handleRoutes() {

        get("/movies", (request, response) -> movieService.findAll(), JSON_TRANSFORMER);

        get("/movies/:id", (request, response) -> {

            final String id = request.params(":id");

            return movieService.findById(Integer.parseInt(id));
        }, JSON_TRANSFORMER);

        get("/health", (request, response) -> Health.create("I'am alive!!"), JSON_TRANSFORMER);
    }

    public void handleExceptions() {

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(ApiError.create(exception.getMessage()).toJson());
        });

        exception(MovieNotFoundException.class, (exception, request, response) -> {
            response.status(404);
            response.body(ApiError.create(exception.getMessage()).toJson());
        });
    }
}
