package com.webencyclop.demo.repository.interfaces;

import com.webencyclop.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
}
