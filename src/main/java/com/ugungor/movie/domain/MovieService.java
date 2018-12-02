package com.ugungor.movie.domain;

import java.util.List;

public interface MovieService {

    List<Movie> findAll();

    Movie findById(Integer id);
}
