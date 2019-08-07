package com.webencyclop.demo.service.interfaces;

import java.util.List;
import com.webencyclop.demo.model.Ticket;

public interface TicketService {
    void addTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
    void removeTicket(int id);
    List<Ticket> listTickets();
    Ticket getTicketById(int id);
}
