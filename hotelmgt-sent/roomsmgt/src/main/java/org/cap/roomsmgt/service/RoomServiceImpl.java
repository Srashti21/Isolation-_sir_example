package org.cap.roomsmgt.service;

import org.cap.roomsmgt.dao.IRoomsDao;
import org.cap.roomsmgt.entities.Room;
import org.cap.roomsmgt.exceptions.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomServiceImpl implements IRoomService {

    @Autowired
    private IRoomsDao roomsDao;

    @Override
    public Room findRoomById(int roomId) {
        Optional<Room> optional = roomsDao.findById(roomId);
        if (optional.isPresent()) {
            Room room = optional.get();
            return room;
        }
        throw new RoomNotFoundException("room not found for id=" + roomId);
    }

    @Override
    public Room save(Room room) {
        room = roomsDao.save(room);
        return room;
    }

    @Override
    public void bookRooms(Collection<Integer>roomsId){
        for (Integer id:roomsId){
           Room room= findRoomById(id);
           room.setAvailable(false);
           save(room);
        }

    }

    @Override
    public List<Room>findRoomsById(List<Integer>roomsId){
       List<Room>rooms= roomsDao.findAllById(roomsId);
       return rooms;
    }

    @Override
    public List<Room>availableRooms(){
      List<Room>rooms=  roomsDao.findByAvailable(true);
      return rooms;
    }


}
