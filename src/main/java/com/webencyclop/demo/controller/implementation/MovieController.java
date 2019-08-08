package com.webencyclop.demo.controller.implementation;

import java.util.List;
import com.webencyclop.demo.controller.interfaces.BaseMovieController;
import com.webencyclop.demo.model.Movie;
import com.webencyclop.demo.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MovieController implements BaseMovieController {

    @Autowired
    private MovieService movieService;

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
