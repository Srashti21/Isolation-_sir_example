package org.cap.bookingmgt.service;

import org.cap.bookingmgt.dao.BookingDao;
import org.cap.bookingmgt.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService{

    @Autowired
    private BookingDao dao;


    @Override
    public Booking save(Booking booking){
        booking=dao.save(booking);
       return booking;
    }

    @Override
    public Booking createBooking( int customerId,int roomsCount,List<Integer>roomsId){
        Booking booking=new Booking();
        booking.setRoomsCount(roomsCount);
        booking.setRoomsId(roomsId);
        booking.setCustomerId(customerId);
        booking=save(booking);
        return booking;
    }

    @Override
    public List<Booking>fetchAll(){
        List<Booking>bookings=dao.findAll();
        return bookings;
    }


}
