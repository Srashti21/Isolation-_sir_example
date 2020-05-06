package org.cap.roomsmgt.dao;

import org.cap.roomsmgt.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoomsDao extends JpaRepository<Room,Integer> {
    List<Room> findByAvailable(boolean available);
}
