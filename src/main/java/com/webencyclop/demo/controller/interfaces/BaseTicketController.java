package com.webencyclop.demo.controller.interfaces;

import com.webencyclop.demo.model.Ticket;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/admin")
public interface BaseTicketController {
    @RequestMapping("/listTickets")
    ModelAndView showListTicket();

    @RequestMapping(value = "/newTicket",method = RequestMethod.GET)
    ModelAndView showPageAddMovie();

    @RequestMapping(value = "/newTicket",method = RequestMethod.POST)
    ModelAndView saveTicket(@Valid Ticket ticket);

    @RequestMapping(value = "/editTicket/{id}",method = RequestMethod.GET)
    ModelAndView showPageEditTicket(@PathVariable int id);

    @RequestMapping(value = "/editTicket",method = RequestMethod.PUT)
    ModelAndView editTicket(@Valid Ticket ticket);

    @RequestMapping(value = "/deleteTicket/{id}",method = RequestMethod.DELETE)
    ModelAndView deleteTicket(@PathVariable int id);

    @RequestMapping(value = "/detailTicket/{id}")
    ModelAndView detailsTicket(@PathVariable int id);
}
