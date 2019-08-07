package com.webencyclop.demo.service.implementation;

import com.webencyclop.demo.model.Ticket;
import com.webencyclop.demo.repository.TicketRepository;
import com.webencyclop.demo.service.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        Ticket updatedTicket = ticketRepository.getOne(ticket.getId());
        updatedTicket.setPrice(ticket.getPrice());
        updatedTicket.setPriceByTime(ticket.getPriceByTime());
        ticketRepository.save(updatedTicket);
    }

    @Override
    public void removeTicket(int id) {
        Ticket deletedTicket = ticketRepository.getOne(id);
        ticketRepository.delete(deletedTicket);
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketRepository.getOne(id);
    }

    @Override
    public List<Ticket> listTickets() {
        return ticketRepository.findAll();
    }
}
