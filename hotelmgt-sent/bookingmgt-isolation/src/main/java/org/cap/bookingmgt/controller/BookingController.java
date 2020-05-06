package org.cap.bookingmgt.controller;

import org.cap.bookingmgt.dto.*;
import org.cap.bookingmgt.entities.Booking;
import org.cap.bookingmgt.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private IBookingService service;


    @PostMapping("/new")
    public ResponseEntity<BookingDetailsDto> book(@RequestBody BookingRequestDto requestDto) {
        int customerId = requestDto.getCustomerId();
        int roomsCount = requestDto.getRoomsCount();
        List<Integer>roomsId=requestDto.getRequestedRooms();
        Booking booking = service.createBooking(customerId, roomsCount,roomsId);
        BookingDetailsDto detailsDto=convertBookingDetails(booking);
        ResponseEntity<BookingDetailsDto> response = new ResponseEntity<>(detailsDto, HttpStatus.OK);
        return response;

    }

    @GetMapping
    public ResponseEntity<List<BookingDetailsDto>> fetchAll() {
        List<Booking> bookings = service.fetchAll();
        List<BookingDetailsDto> list = convertBookingDetails(bookings);
        ResponseEntity<List<BookingDetailsDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
        return response;
    }


    Customer fetchCustomerById(int id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("shivam");
        return customer;
    }


    public List<BookingDetailsDto> convertBookingDetails(List<Booking> bookings) {
        List<BookingDetailsDto> list = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDetailsDto detailsDto = convertBookingDetails(booking);
            list.add(detailsDto);
        }
        return list;
    }

    BookingDetailsDto convertBookingDetails(Booking booking) {
        BookingDetailsDto detailsDto = new BookingDetailsDto();
        detailsDto.setBookingId(booking.getId());
        int customerId = booking.getCustomerId();
        detailsDto.setCustomerId(customerId);
        Customer customer = fetchCustomerById(customerId);
        detailsDto.setCustomerName(customer.getName());
        List<Integer>roomsId=booking.getRoomsId();
        List<Room>rooms =fetchRoomsById(roomsId);
        detailsDto.setRooms(rooms);
        return detailsDto;
    }



    public List<Room> fetchRoomsById(List<Integer> roomsId) {
        Room room1 = new Room();
        room1.setFloorNo(1);
        room1.setRoomNumber(5);

        Room room2 = new Room();
        room2.setFloorNo(2);
        room2.setRoomNumber(4);
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room2);
        return list;
    }


}
