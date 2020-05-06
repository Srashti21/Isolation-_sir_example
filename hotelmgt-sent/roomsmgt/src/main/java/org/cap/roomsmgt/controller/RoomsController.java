package org.cap.roomsmgt.controller;

import org.cap.roomsmgt.dtos.RoomDetailsDto;
import org.cap.roomsmgt.entities.Room;
import org.cap.roomsmgt.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    @Autowired
    private IRoomService service;

    @GetMapping("/available")
    public ResponseEntity<List<RoomDetailsDto>> avaliableRooms() {
        List<Room> rooms = service.availableRooms();
        List<RoomDetailsDto> dtos = convertToDto(rooms);
        ResponseEntity<List<RoomDetailsDto>> response = new ResponseEntity<>(dtos, HttpStatus.OK);
        return response;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<RoomDetailsDto> getRoom(@PathVariable("id") int id) {
        Room room = service.findRoomById(id);
        RoomDetailsDto dto = convertToDto(room);
        ResponseEntity<RoomDetailsDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
        return response;
    }

    @PutMapping("/booked")
    public ResponseEntity booked(@RequestBody Integer[]roomsId) {
        List<Integer>idsList=Arrays.asList(roomsId);
        service.bookRooms(idsList);
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }

    public List<RoomDetailsDto> convertToDto(Collection<Room> rooms) {
        List<RoomDetailsDto> dtos = new ArrayList<>();
        for (Room room : rooms) {
            RoomDetailsDto dto = convertToDto(room);
            dtos.add(dto);
        }
        return dtos;
    }

    RoomDetailsDto convertToDto(Room room) {
        RoomDetailsDto dto = new RoomDetailsDto();
        dto.setFloorNo(room.getFloorNo());
        dto.setRoomNo(room.getRoomNo());
        dto.setId(room.getId());
        return dto;
    }

    @PostConstruct
    public void createRooms() {
        Room room1 = new Room();
        room1.setAvailable(true);
        room1.setFloorNo(1);
        room1.setRoomNo(2);
        room1 = service.save(room1);

        Room room2 = new Room();
        room2.setAvailable(true);
        room2.setFloorNo(1);
        room2.setRoomNo(3);
        room2 = service.save(room2);
    }

}
