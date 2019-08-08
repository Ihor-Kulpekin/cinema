package com.webencyclop.demo.controller.interfaces;

import com.webencyclop.demo.model.Ticket;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


public interface BaseTicketController {
    @GetMapping("/admin/listTickets")
    ModelAndView showListTicket();

    @GetMapping("/admin/newTicket")
    ModelAndView showPageAddMovie();

    @PostMapping("/admin/newTicket")
    ModelAndView saveTicket(@Valid Ticket ticket);

    @GetMapping("/admin/editTicket/{id}")
    ModelAndView showPageEditTicket(@PathVariable int id);

    @PostMapping("/admin/editTicket")
    ModelAndView editTicket(@Valid Ticket ticket);

    @GetMapping("/admin/deleteTicket/{id}")
    ModelAndView deleteTicket(@PathVariable int id);
}
