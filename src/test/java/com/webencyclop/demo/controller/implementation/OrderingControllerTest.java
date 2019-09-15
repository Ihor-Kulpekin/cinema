package com.webencyclop.demo.controller.implementation;

import com.webencyclop.demo.controller.implementation.forUser.OrderingController;
import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.repository.interfaces.RoleRepository;
import com.webencyclop.demo.service.interfaces.forAdmin.MovieService;
import com.webencyclop.demo.service.interfaces.forAdmin.RoomService;
import com.webencyclop.demo.service.interfaces.forAdmin.TicketService;
import com.webencyclop.demo.service.interfaces.forUser.OrderingService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

import java.util.Date;

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
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders
                    .standaloneSetup(orderingController)
                    .setSingleView(view)
                    .build();

    }

    @Test
    public void showPageOrderingTest() throws Exception {
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

}