package org.cap.bookingmgt.service;

import org.cap.bookingmgt.entities.Booking;

import java.util.List;

public interface IBookingService {
    Booking save(Booking booking);

    Booking createBooking(int customerId, int roomsCount,List<Integer>roomsId);

    List<Booking> fetchAll();
}
