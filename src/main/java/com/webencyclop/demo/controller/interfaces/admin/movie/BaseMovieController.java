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
    ModelAndView showListMovies();

    @GetMapping("/admin/new")
    ModelAndView showPageAddMovie();

    @PostMapping("/admin/new")
    String saveMovie(@Valid Movie movie);

    @GetMapping("/admin/edit/{id}")
    ModelAndView showPageEditMovie(@PathVariable int id);

    @PostMapping("/admin/edit")
    String editMovie(@Valid Movie movie);

    @DeleteMapping("/admin/delete/{id}")
    ModelAndView deleteMovie(@PathVariable int id);

    @GetMapping("/admin/detail/{id}")
    ModelAndView detailsMovie(@PathVariable int id);
}
