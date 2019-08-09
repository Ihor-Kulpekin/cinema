package com.webencyclop.demo.controller.implementation;

import com.webencyclop.demo.controller.interfaces.BaseIndexController;
import com.webencyclop.demo.model.Movie;
import com.webencyclop.demo.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController implements BaseIndexController {

    @Autowired
    private MovieService movieService;

    @Override
    public ModelAndView showIndexPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<Movie> movieList = movieService.listMovies();
        modelAndView.addObject("movieList",movieList);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}