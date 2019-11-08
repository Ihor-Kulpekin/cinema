package com.webencyclop.demo.service.implementation.forAdmin;

import com.webencyclop.demo.model.forAdmin.Movie;
import com.webencyclop.demo.repository.interfaces.forAdmin.MovieRepository;
import com.webencyclop.demo.service.interfaces.forAdmin.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        Movie updatedMovie = movieRepository.getOne(movie.getId());
        updatedMovie.setDiscription(movie.getDiscription());
        updatedMovie.setDirector(movie.getDirector());
        updatedMovie.setGenre(movie.getGenre());
        updatedMovie.setDuration(movie.getDuration());
        updatedMovie.setGraduationYear(movie.getGraduationYear());
        updatedMovie.setMainRoles(movie.getMainRoles());
        updatedMovie.setUrlImage(movie.getUrlImage());
        updatedMovie.setUrlTrailer(movie.getUrlTrailer());
        updatedMovie.setNameMovie(movie.getNameMovie());
        movieRepository.save(updatedMovie);
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.getOne(id);
    }

    @Override
    public void removeMovie(int id) {
        Movie deletedMovie = movieRepository.getOne(id);
        movieRepository.delete(deletedMovie);
    }

    @Cacheable("movies")
    @Override
    public List<Movie> listMovies() {
        return movieRepository.findAll();
    }

}
