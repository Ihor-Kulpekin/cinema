package com.webencyclop.demo.repository.interfaces.forAdmin;

import com.webencyclop.demo.model.forAdmin.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
}
