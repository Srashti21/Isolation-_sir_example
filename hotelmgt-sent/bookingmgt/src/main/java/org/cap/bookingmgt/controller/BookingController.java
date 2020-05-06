package org.cap.bookingmgt.controller;

import org.cap.bookingmgt.dto.*;
import org.cap.bookingmgt.entities.Booking;
import org.cap.bookingmgt.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private IBookingService service;

    @Value("${customerservice.baseurl}")
    private String customerServiceBaseUrl;

    @Value("${roomservice.baseurl}")
    private String roomServiceBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/new")
    public ResponseEntity<BookingDetailsDto> book(@RequestBody BookingRequestDto requestDto) {
        int customerId = requestDto.getCustomerId();
        int roomsCount = requestDto.getRoomsCount();
        List<Integer> roomsId = requestDto.getRequestedRooms();
        Booking booking = service.createBooking(customerId, roomsCount, roomsId);
        makeRoomUnAvailable(roomsId);
        BookingDetailsDto detailsDto = convertBookingDetails(booking);
        ResponseEntity<BookingDetailsDto> response = new ResponseEntity<>(detailsDto, HttpStatus.OK);
        return response;

    }

    public void makeRoomUnAvailable(List<Integer> rooms) {
        String url = roomServiceBaseUrl + "/booked";
        restTemplate.put(url, rooms);

    }

    @GetMapping
    public ResponseEntity<List<BookingDetailsDto>> fetchAll() {
        List<Booking> bookings = service.fetchAll();
        List<BookingDetailsDto> list = convertBookingDetails(bookings);
        ResponseEntity<List<BookingDetailsDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
        return response;
    }

    Customer fetchCustomerById(int id) {
        String url = customerServiceBaseUrl + "/get/" + id;
        Customer customer = restTemplate.getForObject(url, Customer.class);
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
        List<Integer> roomsId = booking.getRoomsId();
        List<Room> rooms = fetchRoomsById(roomsId);
        detailsDto.setRooms(rooms);
        return detailsDto;
    }

    public Room fetchRoomById(int roomId) {
        String url = roomServiceBaseUrl + "/get/" + roomId;
        Room room = restTemplate.getForObject(url, Room.class);
        return room;
    }

    public List<Room> fetchRoomsById(List<Integer> roomsId) {
        List<Room> rooms = new ArrayList<>();
        for (int roomId : roomsId) {
            Room room = fetchRoomById(roomId);
            rooms.add(room);
        }
        return rooms;
    }

}
