package com.webencyclop.demo.controller.implementation;

import java.util.Date;
import java.util.List;
import com.webencyclop.demo.controller.interfaces.BaseOrderingController;
import com.webencyclop.demo.model.*;
import com.webencyclop.demo.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderingController implements BaseOrderingController  {

    @Autowired
    private OrderingService orderingService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;


    @Override
    public String showPageOrdering() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Movie> movieList = movieService.listMovies();
        List<Room> roomList = roomService.listRooms();
        List<Ticket> ticketList = ticketService.listTickets();
        modelAndView.addObject("user",user);
        modelAndView.addObject("movieList",movieList);
        modelAndView.addObject("ticketList",ticketList);
        modelAndView.addObject("roomList",roomList);
        modelAndView.addObject("ordering",new Ordering());
        return "doOrdering";
    }


    @Override
    public ModelAndView buyTicket(Ordering ordering) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if(user!=null){
            ordering.setTicketId(ordering.getTicketId());
            ordering.setUserId(user);
            ordering.setRoomId(ordering.getRoomId());
            ordering.setSumPriceTicket(ordering.getSumPriceTicket());
            ordering.setDateOrdering(new Date());
            ordering.setMovieId(ordering.getMovieId());
            modelAndView.setViewName("index");
        }else {
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

}
