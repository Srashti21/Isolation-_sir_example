package org.cap.roomsmgt.service;

import org.cap.roomsmgt.entities.Room;

import java.util.Collection;
import java.util.List;

public interface IRoomService {
    Room findRoomById(int roomId);

    Room save(Room room);

    List<Room> findRoomsById(List<Integer>roomsId);

    List<Room>availableRooms();

    void bookRooms(Collection<Integer> roomsId);
}
