package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.controller.interfaces.forUser.BaseOrderingController;
import com.webencyclop.demo.model.*;
import com.webencyclop.demo.model.forAdmin.Movie;
import com.webencyclop.demo.model.forAdmin.Room;
import com.webencyclop.demo.model.forAdmin.Ticket;
import com.webencyclop.demo.service.interfaces.forAdmin.MovieService;
import com.webencyclop.demo.service.interfaces.forAdmin.RoomService;
import com.webencyclop.demo.service.interfaces.forAdmin.TicketService;
import com.webencyclop.demo.service.interfaces.forUser.OrderingService;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

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
    public ModelAndView showPageOrdering() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("doOrdering");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Movie> movieList = movieService.listMovies();
        List<Room> roomList = roomService.listRooms();
        List<Ticket> ticketList = ticketService.listTickets();
        Ordering ordering = new Ordering();
        modelAndView.addObject("user",user);
        modelAndView.addObject("movieList",movieList);
        modelAndView.addObject("ticketList",ticketList);
        modelAndView.addObject("roomList",roomList);
        modelAndView.addObject("ordering",ordering);
        return modelAndView;
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
            ordering.setSum_price_ticket(ordering.getSum_price_ticket());
            ordering.setDateOrdering(new Date());
            ordering.setMovieId(ordering.getMovieId());
            orderingService.doOrdering(ordering);
            modelAndView.setViewName("redirect:/");
        }else {
            modelAndView.setViewName("redirect:/error");
        }
        return modelAndView;
    }

}
