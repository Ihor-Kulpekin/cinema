package com.webencyclop.demo.controller.interfaces.admin.movie;


import com.webencyclop.demo.model.forAdmin.Movie;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

public interface BaseMovieController {

    @GetMapping("/admin/listMovies")
    ModelAndView listMovies();

    @GetMapping("/admin/listMovies")
    ModelAndView showPageAddMovie();

    @PostMapping("/admin/listMovies")
    String saveMovie(@Valid Movie movie);

    @GetMapping("/admin/listMovies/{id}")
    ModelAndView showPageEditMovie(@PathVariable int id);

    @PostMapping("/admin/listMovies")
    String editMovie(@Valid Movie movie);

    @DeleteMapping("/admin/listMovies/{id}")
    ModelAndView deleteMovie(@PathVariable int id);

    @GetMapping("/admin/listMovies/{id}")
    ModelAndView detailsMovie(@PathVariable int id);
}
