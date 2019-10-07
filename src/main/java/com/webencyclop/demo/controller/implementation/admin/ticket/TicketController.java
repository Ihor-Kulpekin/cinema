package com.webencyclop.demo.controller.implementation.admin.ticket;

import com.webencyclop.demo.controller.interfaces.admin.ticket.BaseTicketController;
import com.webencyclop.demo.model.forAdmin.Ticket;
import com.webencyclop.demo.service.interfaces.forAdmin.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TicketController implements BaseTicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public ModelAndView showListTicket() {
        ModelAndView modelAndView = new ModelAndView();
        List<Ticket> ticketList = ticketService.listTickets();
        modelAndView.addObject("ticketList",ticketList);
        modelAndView.setViewName("listTickets");
        return modelAndView;
    }

    @Override
    public ModelAndView showPageAddTicket() {
        ModelAndView modelAndView = new ModelAndView();
        Ticket ticket = new Ticket();
        modelAndView.addObject("ticket",ticket);
        modelAndView.setViewName("pageAddTicket");
        return modelAndView;
    }

    @Override
    public ModelAndView saveTicket(@Valid Ticket ticket) {
        ModelAndView modelAndView = new ModelAndView();
        ticketService.addTicket(ticket);
        modelAndView.setViewName("redirect:/listTickets");
        return modelAndView;
    }

    @Override
    public ModelAndView showPageEditTicket(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("editTicket");
        Ticket ticket = ticketService.getTicketById(id);
        modelAndView.addObject("ticket",ticket);
        return modelAndView;
    }

    @Override
    public ModelAndView editTicket(@Valid Ticket ticket) {
        ModelAndView modelAndView = new ModelAndView("listTickets");
        ticketService.updateTicket(ticket);
        return modelAndView;
    }

    @Override
    public ModelAndView deleteTicket(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("listTickets");
        ticketService.removeTicket(id);
        return modelAndView;
    }
}
