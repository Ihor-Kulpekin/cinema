package com.webencyclop.demo.controller.implementation;

import java.util.List;
import com.webencyclop.demo.controller.interfaces.BaseRoomController;
import com.webencyclop.demo.model.Room;
import com.webencyclop.demo.service.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RoomController implements BaseRoomController {

    @Autowired
    private RoomService roomService;

    @Override
    public ModelAndView showListRooms() {
        ModelAndView modelAndView = new ModelAndView();
        List<Room> roomList = roomService.listRooms();
        modelAndView.addObject("roomList",roomList);
        modelAndView.setViewName("listRooms");
        return modelAndView;
    }

    @Override
    public ModelAndView showPageAddMovie() {
        ModelAndView modelAndView = new ModelAndView();
        Room room = new Room();
        modelAndView.addObject("room",room);
        modelAndView.setViewName("pageAddRoom");
        return modelAndView;
    }

    @Override
    public String saveMovie(@Valid Room room) {
        roomService.addRoom(room);
        return "redirect:/admin/listRooms";
    }

    @Override
    public ModelAndView showPageEditRoom(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("editRoom");
        Room room = roomService.getRoomById(id);
        modelAndView.addObject("room",room);
        return modelAndView;
    }

    @Override
    public String editRoom(@Valid Room room) {
        roomService.updateRoom(room);
        return "redirect:/admin/listRooms";
    }

    @Override
    public String deleteRoom(@PathVariable int id) {
        roomService.removeRoom(id);
        return "redirect:/admin/listRooms";
    }

    @Override
    public ModelAndView detailsRoom(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("detailsRoom");
        Room room = roomService.getRoomById(id);
        modelAndView.addObject("room",room);
        return modelAndView;
    }
}
