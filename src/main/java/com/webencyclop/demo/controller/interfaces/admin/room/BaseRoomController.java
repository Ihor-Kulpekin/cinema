package com.webencyclop.demo.controller.interfaces.admin.room;

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

    @GetMapping("/admin/listRooms")
    ModelAndView showPageAddMovie();

    @PostMapping("/admin/listRooms")
    String saveMovie(@Valid Room room);

    @GetMapping("/admin/listRooms/{id}")
    ModelAndView showPageEditRoom(@PathVariable int id);

    @PostMapping("/admin/listRooms")
    String editRoom(@Valid Room room);

    @GetMapping("/admin/listRooms/{id}")
    String deleteRoom(@PathVariable int id);

    @RequestMapping(value = "/admin/listRooms/{id}")
    ModelAndView detailsRoom(@PathVariable int id);
}
