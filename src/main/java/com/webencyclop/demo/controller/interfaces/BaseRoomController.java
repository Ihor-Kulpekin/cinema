package com.webencyclop.demo.controller.interfaces;

import com.webencyclop.demo.model.Movie;
import com.webencyclop.demo.model.Room;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/admin")
public interface BaseRoomController {
    @RequestMapping("/listRooms")
    ModelAndView showListRooms();

    @RequestMapping(value = "/newRoom",method = RequestMethod.GET)
    ModelAndView showPageAddMovie();

    @RequestMapping(value = "/newRoom",method = RequestMethod.POST)
    ModelAndView saveMovie(@Valid Room room);

    @RequestMapping(value = "/editRoom/{id}",method = RequestMethod.GET)
    ModelAndView showPageEditRoom(@PathVariable int id);

    @RequestMapping(value = "/editRoom",method = RequestMethod.PUT)
    ModelAndView editRoom(@Valid Room room);

    @RequestMapping(value = "/deleteRoom/{id}",method = RequestMethod.DELETE)
    ModelAndView deleteRoom(@PathVariable int id);

    @RequestMapping(value = "/detailRoom/{id}")
    ModelAndView detailsRoom(@PathVariable int id);
}
