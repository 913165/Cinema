package com.cinema.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.booking.model.Bookings;
import com.cinema.booking.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public void bookMovieTicket(Bookings booking) {
		bookingRepository.save(booking);
	}

	@Override
	public List<Bookings> getCurrentBookings() {
		return bookingRepository.findAll();
	}
}
