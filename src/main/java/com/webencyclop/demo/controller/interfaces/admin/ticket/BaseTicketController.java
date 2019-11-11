package com.webencyclop.demo.controller.interfaces.admin.ticket;

import com.webencyclop.demo.model.forAdmin.Ticket;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


public interface BaseTicketController {
    @GetMapping("/admin/listTickets")
    ModelAndView showListTicket();

    @GetMapping("/admin/listTickets")
    ModelAndView showPageAddTicket();

    @PostMapping("/admin/listTickets")
    ModelAndView saveTicket(@Valid Ticket ticket);

    @GetMapping("/admin/listTickets/{id}")
    ModelAndView showPageEditTicket(@PathVariable int id);

    @PostMapping("/admin/listTickets")
    ModelAndView editTicket(@Valid Ticket ticket);

    @GetMapping("/admin/listTickets/{id}")
    ModelAndView deleteTicket(@PathVariable int id);
}
