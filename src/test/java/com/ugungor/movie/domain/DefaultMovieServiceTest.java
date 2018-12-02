package com.ugungor.movie.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultMovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private DefaultMovieService movieService;

    @Test(expected = MovieNotFoundException.class)
    public void findAllShouldThrowMovieNotFoundExceptionWhenNoMoviesExistInSystem() {

        when(movieRepository.findAll()).thenReturn(Collections.emptyList());

        movieService.findAll();
    }

    @Test(expected = MovieNotFoundException.class)
    public void findByIdShouldThrowMovieNotFoundExceptionWhenNoMovieExistsInSystemWithGivenId() {

        final Integer id = 9999;

        when(movieRepository.findById(id)).thenReturn(Optional.empty());

        movieService.findById(id);
    }
}