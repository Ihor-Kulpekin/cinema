package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.model.forUser.Ordering;
import com.webencyclop.demo.model.forUser.Role;
import com.webencyclop.demo.model.forUser.User;
import com.webencyclop.demo.repository.interfaces.forUser.RoleRepository;
import com.webencyclop.demo.service.interfaces.forAdmin.MovieService;
import com.webencyclop.demo.service.interfaces.forAdmin.RoomService;
import com.webencyclop.demo.service.interfaces.forAdmin.TicketService;
import com.webencyclop.demo.service.interfaces.forUser.ordering.OrderingService;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// There are errors in showPageOrderingTest method

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class OrderingControllerTest {

    @InjectMocks
    private OrderingController orderingController;

    @Mock
    private OrderingService orderingService;

    @Mock
    private TicketService ticketService;

    @Mock
    private MovieService movieService;

    @Mock
    private UserService userService;

    @Mock
    private RoomService roomService;

    @Mock
    private View view;

    @Mock
    private RoleRepository roleRepository;

    private MockMvc mockMvc;

    private Ordering exceptedOrdering;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders
                    .standaloneSetup(orderingController)
                    .setSingleView(view)
                    .build();
    }

    @Test
    public void showPageOrderingTest() throws Exception {
        String urlPage = "/home/buyTicket";
        String nameView = "doOrdering";

        MvcResult mvcResult = mockMvc.perform(get(urlPage))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("ordering",defineExceptedOrderingTest()))
                .andExpect(model().attribute("user",defineUser()))
                .andExpect(view().name(nameView))
                .andReturn();

        int resultStatusCode = mvcResult.getResponse().getStatus();
        int expectedStatusCode = 200;
        assertEquals(expectedStatusCode,resultStatusCode);
    }

    @Test
    public void buyTicketTest() {
    }

    private Ordering defineExceptedOrderingTest(){
        exceptedOrdering = new Ordering();
        exceptedOrdering.setId(1);
        exceptedOrdering.setDateOrdering(new Date());
        exceptedOrdering.setSum_price_ticket(90);
        exceptedOrdering.setMovieId(movieService.getMovieById(8));
        exceptedOrdering.setRoomId(roomService.getRoomById(1));
        exceptedOrdering.setUserId(userService.findUserByEmail("admin@gmail.com"));
        exceptedOrdering.setTicketId(ticketService.getTicketById(1));

        return exceptedOrdering;
    }

    private User defineUser(){
        User user = new User();
        user.setId(1);
        user.setName("Ihor");
        user.setLastName("Kulpekin");
        user.setEmail("sasha@gmail.com");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        user.setStatus("VERIFIED");
        user.setPassword("123456789");

        return user;
    }

}