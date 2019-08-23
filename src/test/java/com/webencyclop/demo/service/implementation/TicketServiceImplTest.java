package com.webencyclop.demo.service.implementation;

import java.util.List;
import com.webencyclop.demo.model.Ticket;
import com.webencyclop.demo.service.interfaces.TicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class TicketServiceImplTest {

    @Autowired
    private TicketService ticketService;


    private Ticket expectedTicket;

    @Before
    public void setUp() throws Exception {
        expectedTicket = new Ticket();
        expectedTicket.setId(1);
        expectedTicket.setPrice(90);
        expectedTicket.setPriceByTime("18:00");
    }

    @Test
    public void addTicket() {
        ticketService.addTicket(expectedTicket);
        Ticket resultTicket = ticketService.getTicketById(expectedTicket.getId());
        assertEquals(expectedTicket.getId(),resultTicket.getId());
    }

    @Test
    public void updateTicket() {
        Ticket resultTicket = new Ticket();
        resultTicket.setId(expectedTicket.getId());
        resultTicket.setPrice(expectedTicket.getPrice());
        resultTicket.setPriceByTime(expectedTicket.getPriceByTime());
        ticketService.addTicket(resultTicket);
        resultTicket.setPrice(20);
        ticketService.updateTicket(resultTicket);
        assertNotEquals(expectedTicket.getPrice(),resultTicket.getPrice());
    }

    @Test
    public void removeTicket() {
        ticketService.removeTicket(expectedTicket.getId());
        assertEquals(1L,ticketService.listTickets().size());
    }

    @Test
    public void getTicketById() {
        List<Ticket> ticketList = ticketService.listTickets();
        Ticket ticketFromList = ticketList.get(0);
        Ticket ticketGotById = ticketService.getTicketById(ticketFromList.getId());
        assertNotNull(ticketGotById);
    }

    @Test
    public void listTickets() {
        assertEquals(2L,ticketService.listTickets().size());
    }
}