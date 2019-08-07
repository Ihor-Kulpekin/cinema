package com.webencyclop.demo.controller.implementation;

import java.util.List;
import com.webencyclop.demo.controller.interfaces.BaseRoomController;
import com.webencyclop.demo.model.Room;
import com.webencyclop.demo.service.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    public ModelAndView saveMovie(@Valid Room room) {
        ModelAndView modelAndView = new ModelAndView();
        roomService.addRoom(room);
        modelAndView.setViewName("listRooms");
        return modelAndView;
    }

    @Override
    public ModelAndView showPageEditRoom(int id) {
        ModelAndView modelAndView = new ModelAndView("editRoom");
        Room room = roomService.getRoomById(id);
        modelAndView.addObject("room",room);
        return modelAndView;
    }

    @Override
    public ModelAndView editRoom(@Valid Room room) {
        ModelAndView modelAndView = new ModelAndView("listRooms");
        roomService.updateRoom(room);
        return modelAndView;
    }

    @Override
    public ModelAndView deleteRoom(int id) {
        ModelAndView modelAndView = new ModelAndView("listRooms");
        roomService.removeRoom(id);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsRoom(int id) {
        ModelAndView modelAndView = new ModelAndView("detailsRoom");
        Room room = roomService.getRoomById(id);
        modelAndView.addObject("room",room);
        return modelAndView;
    }
}
