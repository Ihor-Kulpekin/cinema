package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.controller.interfaces.forUser.ordering.BaseOrderingController;
import com.webencyclop.demo.model.forUser.Ordering;
import com.webencyclop.demo.model.forUser.User;
import com.webencyclop.demo.model.forAdmin.Movie;
import com.webencyclop.demo.model.forAdmin.Room;
import com.webencyclop.demo.model.forAdmin.Ticket;
import com.webencyclop.demo.service.interfaces.forAdmin.MovieService;
import com.webencyclop.demo.service.interfaces.forAdmin.RoomService;
import com.webencyclop.demo.service.interfaces.forAdmin.TicketService;
import com.webencyclop.demo.service.interfaces.forUser.ordering.OrderingService;
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

    private final OrderingService orderingService;
    private final TicketService ticketService;
    private final MovieService movieService;
    private final UserService userService;
    private final RoomService roomService;

    @Autowired
    public OrderingController(OrderingService orderingService,
                              TicketService ticketService,
                              MovieService movieService,
                              UserService userService,
                              RoomService roomService) {
        this.orderingService = orderingService;
        this.ticketService = ticketService;
        this.movieService = movieService;
        this.userService = userService;
        this.roomService = roomService;
    }


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
