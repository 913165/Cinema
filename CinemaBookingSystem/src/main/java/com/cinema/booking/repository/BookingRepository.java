package com.cinema.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinema.booking.model.Bookings;
@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long>{
    
}
