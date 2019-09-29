package com.webencyclop.demo.controller.implementation.forUser;

import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.interfaces.forUser.RoleRepository;
import com.webencyclop.demo.service.interfaces.forAdmin.MovieService;
import com.webencyclop.demo.service.interfaces.forAdmin.RoomService;
import com.webencyclop.demo.service.interfaces.forAdmin.TicketService;
import com.webencyclop.demo.service.interfaces.forUser.OrderingService;
import com.webencyclop.demo.service.interfaces.forUser.UserHistoryOrderingsService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Rollback
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserHistoryControllerTest {

    @InjectMocks
    private UserHistoryController userHistoryController;

    @Mock
    private TicketService ticketService;

    @Mock
    private MovieService movieService;

    @Mock
    private UserService userService;

    @Mock
    private RoomService roomService;
    @Mock
    private UserHistoryOrderingsService userHistoryOrderingsService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private OrderingService orderingService;

    @Mock
    private View view;

    private MockMvc mockMvc;

    private Ordering expectedOrdering;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userHistoryController)
                .setSingleView(view)
                .build();
        defineExceptedOrderingTest();
    }

    @Test
    public void showPageUserHistoryOrdering() throws Exception {
        String urlPage = "/home/userHistoryOrdering";
        String nameView = "showPageUserHistoryOrdering";
        Ordering ordering = defineExceptedOrderingTest();
        User storedUser = defineUser();
        doNothing().when(orderingService).doOrdering(ordering);
        doNothing().when(userService).saveUser(storedUser);
        doNothing().when(orderingService).doOrdering(expectedOrdering);
        User userId = userService.findUserByEmail(storedUser.getEmail());
        List<Ordering> listOrderingsByUserId = userHistoryOrderingsService.findByUserId(userId);
        when(userHistoryOrderingsService.findByUserId(storedUser)).thenReturn(listOrderingsByUserId);
        MvcResult mvcResult = mockMvc.perform(get(urlPage))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listOrderingsByUserId",listOrderingsByUserId.get(0)))
                .andExpect(view().name(nameView))
                .andReturn();
        int resultStatus = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,resultStatus);
    }

    private Ordering defineExceptedOrderingTest(){
        expectedOrdering = new Ordering();
        expectedOrdering.setId(1);
        expectedOrdering.setDateOrdering(new Date());
        expectedOrdering.setSum_price_ticket(90);
        expectedOrdering.setMovieId(movieService.getMovieById(8));
        expectedOrdering.setRoomId(roomService.getRoomById(1));
        expectedOrdering.setUserId(userService.findUserByEmail("ihor.kulpekin@gmail.com"));
        expectedOrdering.setTicketId(ticketService.getTicketById(1));

        return expectedOrdering;
    }

    private User defineUser(){
        User user = new User();
        user.setId(1);
        user.setName("Ihor");
        user.setLastName("Kulpekin");
        user.setEmail("ihor.kulpekin@gmail.com");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        user.setStatus("VERIFIED");
        user.setPassword("123456789");

        return user;
    }
}