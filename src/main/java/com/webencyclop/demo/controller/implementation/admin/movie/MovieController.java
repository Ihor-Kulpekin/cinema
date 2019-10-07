package com.webencyclop.demo.controller.implementation.admin.movie;

import com.webencyclop.demo.controller.interfaces.admin.movie.BaseMovieController;
import com.webencyclop.demo.model.forAdmin.Movie;
import com.webencyclop.demo.service.interfaces.forAdmin.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MovieController implements BaseMovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public ModelAndView showListMovies() {
        ModelAndView modelAndView = new ModelAndView();
        List<Movie> movieList = movieService.listMovies();
        modelAndView.addObject("movieList",movieList);
        modelAndView.setViewName("listMovies");
        return modelAndView;
    }

    @Override
    public ModelAndView showPageAddMovie() {
        ModelAndView modelAndView = new ModelAndView();
        Movie movie = new Movie();
        modelAndView.addObject("movie",movie);
        modelAndView.setViewName("pageAddMovie");
        return modelAndView;
    }

    @Override
    public String saveMovie(@Valid Movie movie) {
        ModelAndView modelAndView = new ModelAndView();
        movieService.addMovie(movie);
        modelAndView.setViewName("listMovies");
        return "redirect:/listMovies";
    }

    @Override
    public ModelAndView showPageEditMovie(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("editMovie");
        Movie movie = movieService.getMovieById(id);
        modelAndView.addObject("movie",movie);
        return modelAndView;
    }

    @Override
    public String editMovie(@Valid Movie movie) {
        movieService.updateMovie(movie);
        return "redirect:/listMovies";
    }

    @Override
    public ModelAndView deleteMovie(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("listMovies");
        movieService.removeMovie(id);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsMovie(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("detailsMovie");
        Movie movie = movieService.getMovieById(id);
        modelAndView.addObject("movie",movie);
        return modelAndView;
    }
}
