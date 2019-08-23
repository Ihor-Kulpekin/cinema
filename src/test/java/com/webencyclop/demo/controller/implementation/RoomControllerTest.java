package com.webencyclop.demo.controller.implementation;

import com.sun.mail.iap.Argument;
import com.webencyclop.demo.model.Movie;
import com.webencyclop.demo.model.Room;
import com.webencyclop.demo.service.interfaces.RoomService;
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

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.beans.HasProperty.hasProperty;

@Rollback
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoomControllerTest {

    @InjectMocks
    private RoomController roomController;

    @Mock
    private RoomService roomService;

    @Mock
    private View view;

    private MockMvc mockMvc;


    private Room expectedRoom;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(roomController)
                .setSingleView(view)
                .build();
    }

    @Test
    public void showListRoomsTest() throws Exception {
        String url = "/admin/listRooms";
        List<Room> roomList = roomService.listRooms();
        roomList.add(expectedRoom);
        when(roomService.listRooms()).thenReturn(roomList);
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(model().attribute("movie",roomList.get(0)))
                .andExpect(view().name("listRooms"))
                .andReturn();
        int resultStatus = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,resultStatus);
    }

    @Test
    public void showPageAddMovieTest() throws Exception {
        String url = "/admin/newRoom";
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("pageAddRoom"))
                .andReturn();
        int resultStatus = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,resultStatus);
    }

    @Test
    public void saveMovieTest() throws Exception {
        String url = "/admin/newRoom";
        expectedRoom = new Room();
        expectedRoom.setId(1);
        expectedRoom.setNumberRoom("23");
        doNothing().when(roomService).addRoom(expectedRoom);
        mockMvc.perform(post(url)
        .param("id","1")
        .param("numberRoom","23"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("room",instanceOf(Room.class)))
                .andExpect(model().attribute("room",hasProperty("id")))
                .andExpect(model().attribute("room",hasProperty("numberRoom")));
        ArgumentCaptor<Room> boundRoom = ArgumentCaptor.forClass(Room.class);
        verify(roomService).addRoom(boundRoom.capture());
        assertEquals(expectedRoom.getId(),boundRoom.getValue().getId());
    }

    @Test
    public void showPageEditRoomTest() throws Exception {
        String url = "/admin/editRoom/1";
        expectedRoom = new Room();
        expectedRoom.setId(1);
        expectedRoom.setNumberRoom("23");
        when(roomService.getRoomById(expectedRoom.getId())).thenReturn(expectedRoom);
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("editRoom"))
                .andExpect(model().attribute("room",expectedRoom)).andReturn();
        int resultStatus = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,resultStatus);
    }

    @Test
    public void editRoomTest() throws Exception {
        String url = "/admin/editRoom";
        expectedRoom = new Room();
        expectedRoom.setId(1);
        expectedRoom.setNumberRoom("23");
        MvcResult mvcResult = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedRoom.toString()))
                .andReturn();
        int resultStatus = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,resultStatus);
    }

    @Test
    public void deleteRoomTest() throws Exception {
        String url = "/admin/deleteRoom/1";
        expectedRoom = new Room();
        expectedRoom.setId(1);
        expectedRoom.setNumberRoom("23");
        doNothing().when(roomService).addRoom(expectedRoom);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/admin/listRooms"));
        verify(roomService,times(1)).removeRoom(expectedRoom.getId());
    }
}