package com.webencyclop.demo.service.interfaces.forAdmin;

import java.util.List;
import com.webencyclop.demo.model.forAdmin.Room;

public interface RoomService {
    void addRoom(Room room);
    void updateRoom(Room room);
    void removeRoom(int id);
    List<Room> listRooms();
    Room getRoomById(int id);
}
