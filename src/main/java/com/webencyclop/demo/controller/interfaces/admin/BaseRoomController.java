package com.webencyclop.demo.controller.interfaces.admin;

import com.webencyclop.demo.model.forAdmin.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/admin/editRoom")
    String editRoom(@Valid Room room);

    @GetMapping("/admin/deleteRoom/{id}")
    String deleteRoom(@PathVariable int id);

    @RequestMapping(value = "/admin/detailRoom/{id}")
    ModelAndView detailsRoom(@PathVariable int id);
}
