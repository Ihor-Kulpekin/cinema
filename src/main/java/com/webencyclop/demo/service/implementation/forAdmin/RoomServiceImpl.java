package com.webencyclop.demo.service.implementation.forAdmin;

import com.webencyclop.demo.model.forAdmin.Room;
import com.webencyclop.demo.repository.interfaces.forAdmin.RoomRepository;
import com.webencyclop.demo.service.interfaces.forAdmin.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void updateRoom(Room room) {
        Room updatedRoom = roomRepository.getOne(room.getId());
        updatedRoom.setNumberRoom(room.getNumberRoom());
        roomRepository.save(updatedRoom);
    }

    @Override
    public void removeRoom(int id) {
        Room deletedRoom = roomRepository.getOne(id);
        roomRepository.delete(deletedRoom);
    }

    @Override
    public Room getRoomById(int id) {
        return roomRepository.getOne(id);
    }

    @Override
    @Cacheable("rooms")
    public List<Room> listRooms() {
        return roomRepository.findAll();
    }
}
