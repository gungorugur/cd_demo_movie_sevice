package com.ugungor.movie.domain;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    Optional<Movie> findById(Integer id);

    List<Movie> findAll();
}
