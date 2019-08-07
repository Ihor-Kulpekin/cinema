package com.webencyclop.demo.service.interfaces;

import com.webencyclop.demo.model.Movie;

import java.util.List;

public interface MovieService {
    void addMovie(Movie movie);
    void updateMovie(Movie movie);
    Movie getMovieById(int id);
    void removeMovie(int id);
    List<Movie> listMovies();
}
