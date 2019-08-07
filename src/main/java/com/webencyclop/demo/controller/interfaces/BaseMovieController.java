package com.webencyclop.demo.controller.interfaces;


import com.webencyclop.demo.model.Movie;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/admin")
public interface BaseMovieController {

    @RequestMapping("/listMovies")
    ModelAndView showListMovies();

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    ModelAndView showPageAddMovie();

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    ModelAndView saveMovie(@Valid Movie movie);

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    ModelAndView showPageEditMovie(@PathVariable int id);

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    ModelAndView editMovie(@Valid Movie movie);

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    ModelAndView deleteMovie(@PathVariable int id);

    @RequestMapping(value = "/detail/{id}")
    ModelAndView detailsMovie(@PathVariable int id);
}
