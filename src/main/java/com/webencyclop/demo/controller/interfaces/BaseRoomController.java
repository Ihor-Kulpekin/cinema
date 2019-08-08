package com.webencyclop.demo.controller.interfaces;

import com.webencyclop.demo.model.Movie;
import com.webencyclop.demo.model.Room;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

public interface BaseRoomController {
    @GetMapping("/admin/listRooms")
    ModelAndView showListRooms();

    @GetMapping("/admin/newRoom")
    ModelAndView showPageAddMovie();

    @PostMapping("/admin/newRoom")
    String saveMovie(@Valid Room room);

    @GetMapping("/admin/editRoom/{id}")
    ModelAndView showPageEditRoom(@PathVariable int id);

    @RequestMapping(value = "/admin/editRoom",method = RequestMethod.POST)
    String editRoom(@Valid Room room);

    @GetMapping("/admin/deleteRoom/{id}")
    String deleteRoom(@PathVariable int id);

    @RequestMapping(value = "/admin/detailRoom/{id}")
    ModelAndView detailsRoom(@PathVariable int id);
}
