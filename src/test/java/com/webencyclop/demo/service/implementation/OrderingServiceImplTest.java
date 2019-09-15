package com.webencyclop.demo.service.implementation;

import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.service.interfaces.forAdmin.MovieService;
import com.webencyclop.demo.service.interfaces.forAdmin.RoomService;
import com.webencyclop.demo.service.interfaces.forAdmin.TicketService;
import com.webencyclop.demo.service.interfaces.forUser.OrderingService;
import com.webencyclop.demo.service.interfaces.forUser.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@Transactional
@Rollback
@RunWith(SpringRunner.class)
public class OrderingServiceImplTest {

    @Autowired
    private OrderingService orderingService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private TicketService ticketService;

    private Ordering exceptedOrdering;

    @Before
    public void setUp() throws Exception {
        defineExceptedOrderingTest();
    }

    @Test
    public void doOrderingTest() {
        orderingService.doOrdering(exceptedOrdering);
        Ordering resultOrdering = orderingService.getOrderingById(1);

        assertEquals(exceptedOrdering.getId(),resultOrdering.getId());
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