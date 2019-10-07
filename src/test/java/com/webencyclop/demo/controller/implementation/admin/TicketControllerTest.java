package com.webencyclop.demo.controller.implementation.admin;

import com.webencyclop.demo.controller.implementation.admin.ticket.TicketController;
import com.webencyclop.demo.model.forAdmin.Ticket;
import com.webencyclop.demo.service.interfaces.forAdmin.TicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@Rollback
@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private View view;

    @Mock
    private TicketService ticketService;

    private MockMvc mockMvc;

    private Ticket expectedTicket;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(ticketController)
                .setSingleView(view)
                .build();
    }

    @Test
    public void showListTicketTest() throws Exception {
        String url = "/admin/listTickets";
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(expectedTicket);
        when(ticketService.listTickets()).thenReturn(ticketList);
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(model().attribute("ticket",ticketList.get(0)))
                .andExpect(view().name("listTickets"))
                .andReturn();
        int resultStatusCode = mvcResult.getResponse().getStatus();
        int expectedStatusCode = 200;
        assertEquals(expectedStatusCode,resultStatusCode);
    }

    @Test
    public void showPageAddTicketTest() throws Exception {
        String url = "/admin/newTicket";
        expectedTicket = new Ticket();
        expectedTicket.setId(1);
        expectedTicket.setPrice(60);
        expectedTicket.setPriceByTime("18:00");
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("pageAddTicket"))
                .andReturn();
        int resultStatusCode = mvcResult.getResponse().getStatus();
        int expectedStatusCode = 200;
        assertEquals(expectedStatusCode,resultStatusCode);
    }

    @Test
    public void saveTicketTest() throws Exception {
        String url = "/admin/newTicket";
        expectedTicket = new Ticket();
        expectedTicket.setId(1);
        expectedTicket.setPrice(60);
        expectedTicket.setPriceByTime("18:00");
        doNothing().when(ticketService).addTicket(expectedTicket);
        mockMvc.perform(post(url)
            .param("id",String.valueOf(expectedTicket.getId()))
            .param("price", String.valueOf(expectedTicket.getPrice()))
            .param("priceByTime",expectedTicket.getPriceByTime()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/listTickets"))
                .andExpect(model().attribute("ticket",instanceOf(Ticket.class)))
                .andExpect(model().attribute("ticket",hasProperty("id")))
                .andExpect(model().attribute("ticket",hasProperty("price")))
                .andExpect(model().attribute("ticket",hasProperty("priceByTime")));
        ArgumentCaptor<Ticket> boundTicket = ArgumentCaptor.forClass(Ticket.class);
        verify(ticketService).addTicket(boundTicket.capture());
        assertEquals(expectedTicket.getId(),boundTicket.getValue().getId());
    }

    @Test
    public void showPageEditTicketTest() throws Exception {
        String url = "/admin/editTicket/1";
        expectedTicket = new Ticket();
        expectedTicket.setId(1);
        expectedTicket.setPrice(60);
        expectedTicket.setPriceByTime("18:00");
        when(ticketService.getTicketById(expectedTicket.getId())).thenReturn(expectedTicket);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("editTicket"))
                .andExpect(model().attribute("ticket",expectedTicket));
    }

    @Test
    public void editTicketTest() throws Exception {
        String url = "/admin/editTicket";
        expectedTicket = new Ticket();
        expectedTicket.setId(1);
        expectedTicket.setPrice(60);
        expectedTicket.setPriceByTime("18:00");
        MvcResult mvcResult = mockMvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(expectedTicket.toString()))
                    .andReturn();
        int resultStatusCode = mvcResult.getResponse().getStatus();
        int expectedStatusCode = 200;
        assertEquals(expectedStatusCode,resultStatusCode);
    }

    @Test
    public void deleteTicketTest() throws Exception {
        String url = "/admin/deleteTicket/1";
        expectedTicket = new Ticket();
        expectedTicket.setId(1);
        expectedTicket.setPrice(60);
        expectedTicket.setPriceByTime("18:00");
        doNothing().when(ticketService).addTicket(expectedTicket);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("listTickets"));
        verify(ticketService,times(1)).removeTicket(expectedTicket.getId());
    }
}