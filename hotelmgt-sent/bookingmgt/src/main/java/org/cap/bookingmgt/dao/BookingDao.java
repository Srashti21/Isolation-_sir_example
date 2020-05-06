package org.cap.bookingmgt.dao;

import org.cap.bookingmgt.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<Booking,Integer> {
}
