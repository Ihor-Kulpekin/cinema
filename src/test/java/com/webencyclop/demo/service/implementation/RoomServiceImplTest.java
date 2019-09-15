package com.webencyclop.demo.service.implementation;

import java.util.List;
import com.webencyclop.demo.model.forAdmin.Room;
import com.webencyclop.demo.service.interfaces.forAdmin.RoomService;
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
public class RoomServiceImplTest {

    @Autowired
    private RoomService roomService;

    private Room expectedRoom;

    @Before
    public void setUp() throws Exception {
        expectedRoom = new Room();
        expectedRoom.setId(1);
        expectedRoom.setNumberRoom("25");
    }

    @Test
    public void addRoom() {
        roomService.addRoom(expectedRoom);
        Room resultRoom = roomService.getRoomById(1);
        assertEquals(expectedRoom.getId(),resultRoom.getId());
    }

    @Test
    public void updateRoom() {
        Room resultRoom = new Room();
        resultRoom.setId(expectedRoom.getId());
        resultRoom.setNumberRoom(expectedRoom.getNumberRoom());
        roomService.addRoom(resultRoom);
        resultRoom.setNumberRoom("24");
        roomService.updateRoom(resultRoom);
        assertNotEquals(expectedRoom.getNumberRoom(),resultRoom.getNumberRoom());
    }

    @Test
    public void removeRoom() {
        roomService.removeRoom(expectedRoom.getId());
        assertEquals(0L,roomService.listRooms().size());
    }

    @Test
    public void getRoomById() {
        List<Room> roomList = roomService.listRooms();
        Room roomFromList = roomList.get(0);
        Room roomGotByID = roomService.getRoomById(roomFromList.getId());
        assertEquals(expectedRoom.getId(),roomGotByID.getId());
    }

    @Test
    public void listRooms() {
        roomService.addRoom(expectedRoom);
        assertEquals(1L,roomService.listRooms().size());
    }
}