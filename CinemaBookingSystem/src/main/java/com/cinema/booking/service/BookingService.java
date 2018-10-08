package com.cinema.booking.service;

import java.util.List;

import com.cinema.booking.model.Bookings;

public interface BookingService {
     public void bookMovieTicket(Bookings booking);
     public List<Bookings>  getCurrentBookings();
}
